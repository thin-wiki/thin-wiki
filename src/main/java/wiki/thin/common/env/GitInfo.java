package wiki.thin.common.env;

import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Beldon
 */
@Getter
@ToString
public class GitInfo {
    private final String branch;
    private final String buildHost;
    private final Date buildTime;
    private final String buildUserEmail;
    private final String buildUserName;
    private final String buildVersion;
    private final String closestTagCommitCount;
    private final String closestTagName;
    private final String commitId;
    private final String commitIdAbbrev;
    private final String commitIdDescribe;
    private final String commitIdDescribeShort;
    private final String commitMessageFull;
    private final String commitMessageShort;
    private final Date commitTime;
    private final String commitUserEmail;
    private final String commitUserName;
    private final String dirty;
    private final String localBranchAhead;
    private final String localBranchBehind;
    private final String remoteOriginUrl;
    private final String tags;
    private final String totalCommitCount;

    protected GitInfo(Properties properties) {
        this.branch = properties.getProperty("git.branch");
        this.buildHost = properties.getProperty("git.build.host");
        this.buildTime = formatDate(properties.getProperty("git.build.time"));
        this.buildUserEmail = properties.getProperty("git.build.user.email");
        this.buildUserName = properties.getProperty("git.build.user.name");
        this.buildVersion = properties.getProperty("git.build.version");
        this.closestTagCommitCount = properties.getProperty("git.closest.tag.commit.count");
        this.closestTagName = properties.getProperty("git.closest.tag.name");
        this.commitId = properties.getProperty("git.commit.id");
        this.commitIdAbbrev = properties.getProperty("git.commit.id.abbrev");
        this.commitIdDescribe = properties.getProperty("git.commit.id.describe");
        this.commitIdDescribeShort = properties.getProperty("git.commit.id.describe-short");
        this.commitMessageFull = properties.getProperty("git.commit.message.full");
        this.commitMessageShort = properties.getProperty("git.commit.message.short");
        this.commitTime = formatDate(properties.getProperty("git.commit.time"));
        this.commitUserEmail = properties.getProperty("git.commit.user.email");
        this.commitUserName = properties.getProperty("git.commit.user.name");
        this.dirty = properties.getProperty("git.dirty");
        this.localBranchAhead = properties.getProperty("git.local.branch.ahead");
        this.localBranchBehind = properties.getProperty("git.local.branch.behind");
        this.remoteOriginUrl = properties.getProperty("git.remote.origin.url");
        this.tags = properties.getProperty("git.tags");
        this.totalCommitCount = properties.getProperty("git.total.commit.count");
    }

    private static Date formatDate(String dataStr) {
        if (dataStr == null) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            return format.parse(dataStr);
        } catch (Exception e) {
            return null;
        }
    }
}
