package wiki.thin.common.util;

/**
 * @author Beldon
 */
public class HtmlUtils {

    /**
     * 定义script的正则表达式，去除js可以防止注入
     */
    private static final String SCRIPT_REGEX = "<script[^>]*?>[\\s\\S]*?<\\/script>";

    /**
     * 定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
     */
    private static final String STYLE_REGEX = "<style[^>]*?>[\\s\\S]*?<\\/style>";

    /**
     * 定义HTML标签的正则表达式，去除标签，只提取文字内容
     */
    private static final String HTML_REGEX = "<[^>]+>";

    /**
     * 定义空格,回车,换行符,制表符
     */
    private static final String SPACE_REGEX = "\\s*|\t|\r|\n";

    private HtmlUtils() {
    }

    public static String delHtmlTags(String htmlStr) {
        // 过滤script标签
        htmlStr = htmlStr.replaceAll(SCRIPT_REGEX, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(STYLE_REGEX, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(HTML_REGEX, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(SPACE_REGEX, "");
        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        htmlStr = htmlStr.replaceAll("&emsp;", "");
        return htmlStr.trim();
    }

    public static String replace(String content) {
        return content.replaceAll(Character.toString((char) 8203), "")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&gt;", " ")
                .replaceAll("&lt;", " ")
                .replaceAll("\t|\r|\n|�|<|>|�|\\$|\\|", " ")
                .replaceAll("　", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

}
