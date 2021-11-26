package wiki.thin.module.search;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wiki.thin.common.bean.Page;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.module.search.bean.ArticleSearch;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Beldon
 */
@Service
@Slf4j
public class DefaultSearchManager implements SearchManager {

    private static final String DIR = "./data/index";
    private static final int CONTENT_AMX_LENGTH = 100;
    private static final Analyzer ANALYZER = new StandardAnalyzer();

    @Override
    public void index(SearchItemProvider provider) throws IOException {
        try (IndexWriter writer = getWriter()) {
            provider.save(articleSearch -> {
                try {
                    writer.deleteDocuments(new QueryParser("id", ANALYZER).parse(articleSearch.getId().toString()));
                } catch (ParseException e) {
                    log.error("delete documents error", e);
                }

                Document doc = new Document();
                doc.add(new StringField("id", articleSearch.getId().toString(), Field.Store.YES));
                doc.add(new TextField("title", articleSearch.getTitle(), Field.Store.YES));
                doc.add(new TextField("content", articleSearch.getContent(), Field.Store.YES));
                doc.add(new StringField("columnId", articleSearch.getColumnId().toString(), Field.Store.YES));
                doc.add(new StringField("selfSharable", String.valueOf(articleSearch.getSelfSharable().getValue()), Field.Store.YES));
                doc.add(new StringField("finalSharable", String.valueOf(articleSearch.getFinalSharable().getValue()), Field.Store.YES));
                doc.add(new StringField("lastModifiedDate", String.valueOf(articleSearch.getLastModifiedDate().getTime()), Field.Store.YES));
                writer.addDocument(doc);

            });
        }
    }

    @Override
    public void deleteById(Long articleId) throws IOException {
        try (IndexWriter writer = getWriter()) {
            try {
                writer.deleteDocuments(new QueryParser("id", ANALYZER).parse(articleId.toString()));
            } catch (ParseException e) {
                log.error("delete documents error", e);
            }
        }
    }

    @Override
    public void deleteAll() throws IOException {
        try (final IndexWriter writer = getWriter()) {
            writer.deleteAll();
        }
    }

    @Override
    public Page<ArticleSearch> searchAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException {
        QueryParser qp = new MultiFieldQueryParser(new String[]{"title", "content"}, ANALYZER);
        return search(qp.parse(keyword), currentPage, pageSize);
    }

    @Override
    public Page<ArticleSearch> searchSharableAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException {
        QueryParser queryParser = new MultiFieldQueryParser(new String[]{"title", "content"}, ANALYZER);
        final Query query = queryParser.parse(keyword);

        QueryParser finalSharableQueryParser = new QueryParser("finalSharable", ANALYZER);
        Query finalSharableQuery = finalSharableQueryParser.parse(String.valueOf(SharableEnum.SHAREABLE.getValue()));

        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(query, BooleanClause.Occur.MUST);
        builder.add(finalSharableQuery, BooleanClause.Occur.MUST);

        return search(builder.build(), currentPage, pageSize);
    }

    private Page<ArticleSearch> search(Query query, int currentPage, int pageSize) throws IOException {
        try (final IndexReader reader = getReader()) {
            IndexSearcher is = new IndexSearcher(reader);
            TopDocs topDocs = is.search(query, 100);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            int start = (currentPage - 1) * pageSize;
            int end = pageSize * currentPage;
            if (end > scoreDocs.length) {
                end = scoreDocs.length;
            }

            List<ArticleSearch> articleSearches = new ArrayList<>();

            for (int i = start; i < end; i++) {
                Document targetDoc = is.doc(scoreDocs[i].doc);
                ArticleSearch articleSearch = new ArticleSearch();
                articleSearch.setId(Long.valueOf(targetDoc.get("id")));
                articleSearch.setTitle(targetDoc.get("title"));
                articleSearch.setTitle(getHighlighterString("title", query, targetDoc.get("title")));
                articleSearch.setContent(getHighlighterString("content", query, targetDoc.get("content")) + "...");
                articleSearch.setColumnId(Long.valueOf(targetDoc.get("columnId")));

                articleSearch.setSelfSharable(SharableEnum.fromCode(Integer.parseInt(targetDoc.get("selfSharable"))));
                articleSearch.setFinalSharable(SharableEnum.fromCode(Integer.parseInt(targetDoc.get("finalSharable"))));

                articleSearch.setLastModifiedDate(new Date(Long.parseLong(targetDoc.get("lastModifiedDate"))));

                articleSearches.add(articleSearch);
            }
            return new Page<>(currentPage, pageSize, topDocs.totalHits.value, articleSearches);
        }
    }

    private IndexReader getReader() throws IOException {
        return DirectoryReader.open(getDirectory());
    }

    private IndexWriter getWriter() throws IOException {
        final Directory directory = getDirectory();
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
        return new IndexWriter(directory, writerConfig);
    }

    private Directory getDirectory() throws IOException {
        return FSDirectory.open(Paths.get(DIR));
    }

    public String getHighlighterString(String fieldName, Query query, String context) throws IOException {
        QueryScorer scorer = new QueryScorer(query);
        Formatter htmlFormatter = new SimpleHTMLFormatter("<span style='color:red'>", "</span>");
        Highlighter highlighter = new Highlighter(htmlFormatter, scorer);

        highlighter.setTextFragmenter(new SimpleFragmenter(CONTENT_AMX_LENGTH));

        try (final StandardAnalyzer standardAnalyzer = new StandardAnalyzer()) {
            TokenStream tokenStream = standardAnalyzer.tokenStream(fieldName, new StringReader(context));
            final String bestFragment = highlighter.getBestFragment(tokenStream, context);
            if (StringUtils.hasText(bestFragment)) {
                return bestFragment;
            }
        } catch (InvalidTokenOffsetsException e) {
            log.error("high light error", e);
        }

        if (context.length() > CONTENT_AMX_LENGTH) {
            return context.substring(0, CONTENT_AMX_LENGTH);
        }
        return context;
    }
}
