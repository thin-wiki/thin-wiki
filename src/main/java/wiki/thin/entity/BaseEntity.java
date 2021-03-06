package wiki.thin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Beldon
 */
@Data
public abstract class BaseEntity implements Serializable {
    private Long id;
}
