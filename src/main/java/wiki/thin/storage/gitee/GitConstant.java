package wiki.thin.storage.gitee;

/**
 * @author Beldon
 */
public class GitConstant {

    /**
     * 添加文件
     * https://gitee.com/api/v5/swagger#/postV5ReposOwnerRepoContentsPath
     * POST: 添加文件
     * UPDATE: 更新文件
     * DELETE:  删除文件
     */
    public static final String FILE_API = "https://gitee.com/api/v5/repos/{owner}/{repo}/contents/{path}";

    public static final String HTML_URL = "https://gitee.com/{owner}/{repo}/raw/{branch}/{path}";

}
