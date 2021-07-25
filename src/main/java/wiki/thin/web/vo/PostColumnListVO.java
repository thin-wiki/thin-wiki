package wiki.thin.web.vo;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

import java.time.LocalDateTime;

/**
 * @author Beldon
 */
@Data
public class PostColumnListVO {
    private Long id;
    private String path;
    private String title;
    private SharableEnum sharable;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
