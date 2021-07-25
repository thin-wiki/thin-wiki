package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("app_config")
public class AppConfig extends BaseEntity {
    @Column("`type`")
    private String type;
    @Column("`key`")
    private String key;
    @Column("`value`")
    private String value;
    private String description;
}
