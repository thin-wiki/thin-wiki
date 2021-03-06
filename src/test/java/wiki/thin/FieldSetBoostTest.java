package wiki.thin;

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
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

public class FieldSetBoostTest {
    //索引目录
    String indexDir = "E:\\LuceneIndex";
    //测试数据
    String theme = "中国";
    String[] title = {"中国是一个伟大的国家", "我爱你的的祖国,美丽的中国", "是什么，中国令美日等国虎视眈眈"};

    /**
     * Lucence5.5返回IndexWriter实例
     *
     * @param directory
     * @return
     */
    public IndexWriter getIndexWriter(Directory directory) {
//        Analyzer analyzer = new CJKAnalyzer();//中日韩二元分词
        Analyzer analyzer = new StandardAnalyzer();//中日韩二元分词
        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory, writerConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }

    public Directory getDirctory(String indexDir) {
        Directory directory = null;
        try {
            directory = FSDirectory.open(Paths.get(indexDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory;
    }

    /**
     * 创建索引不加权
     *
     * @throws Exception
     */
    public void Indexer() throws Exception {
        IndexWriter writer = getIndexWriter(getDirctory(indexDir));
        Document doc = null;
        for (String str : title) {
            doc = new Document();
            //Lucence5.5 Fileld有多个实现，StringFIeld不分词  TextField分词
            doc.add(new StringField("theme", theme, Field.Store.YES));
            Field field = new TextField("title", str, Field.Store.YES);
            doc.add(field);
            writer.addDocument(doc);
        }
        writer.close();
    }

    /**
     * 关键命中词高亮输出处理
     *
     * @param query
     * @param context
     * @return
     * @throws Exception
     */
    public static String getHighlighterString(Query query, String context) throws Exception {
        //对促成文档匹配的实际项进行评分
        QueryScorer scorer = new QueryScorer(query);
        //设置高亮的HTML标签格式
        Formatter simpleHTMLFormatter = new SimpleHTMLFormatter("", "");
        //实例化高亮分析器
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);

        //使用Lucene简单生成摘要
//		 Fragmenter fragmenter = new SimpleFragmenter(100);
//		 highlighter.setTextFragmenter(fragmenter);
        highlighter.setTextFragmenter(new NullFragmenter()); //不要限制子多少

        //提供静态方法，支持从数据源中获取TokenStream，进行token处理
        TokenStream tokenStream = new StandardAnalyzer().tokenStream("title", new StringReader(context));
        return highlighter.getBestFragment(tokenStream, context);
    }

    @Test
    public void searcherTest() throws Exception {
//          Indexer();
        IndexReader reader = DirectoryReader.open(getDirctory(indexDir));
        IndexSearcher is = new IndexSearcher(reader);
        System.out.println("总的文档数：" + reader.numDocs());
        QueryParser qp = new QueryParser("title", new StandardAnalyzer());
//        QueryParser queryParser2 = new MultiFieldQueryParser(LuceneUtils.getVersion(),new String[]{"title","content"},LuceneUtils.getAnalyzer());

        String q = "中国";
        Query query = qp.parse(q);
        TopDocs tDocs = is.search(query, 11);
        System.out.println("查询-》" + q + "《-总共命中【" + tDocs.totalHits + "】条结果");
        for (ScoreDoc scoredoc : tDocs.scoreDocs) {
            Document doc = is.doc(scoredoc.doc);
            String context = doc.get("title");
            if (context != null) {
                System.out.println(getHighlighterString(query, context));
            }

        }
    }
}
