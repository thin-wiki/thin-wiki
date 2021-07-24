package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Beldon
 */
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable, Persistable<Long> {
    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @CreatedDate
    private LocalDateTime createdDate;
    @Getter
    @Setter
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Transient
    private boolean newEntity;

    @Override
    public boolean isNew() {
        return id == null || newEntity;
    }

    public void markNew() {
        this.newEntity = true;
    }

}
