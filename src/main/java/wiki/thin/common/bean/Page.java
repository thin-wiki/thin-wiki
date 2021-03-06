package wiki.thin.common.bean;

import lombok.Data;

import java.util.List;

/**
 * @param <T> contentå
 * @author beldon
 */
@Data
public class Page<T> {
    private final int currentPage;
    private final int pageSize;
    private final long total;
    private final List<T> content;

    public int getTotalPage() {
        return (int) ((total + pageSize - 1) / pageSize);
    }
}
