package wiki.thin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "note")
@EntityListeners(AuditingEntityListener.class)
public class Note implements Serializable {
    @Id
    @GenericGenerator(name = "customIDGenerator", strategy = "wiki.thin.common.CustomIDGenerator" )
    @GeneratedValue(generator = "customIDGenerator")
    private Long id;

    @Column(columnDefinition = "longtext")
    private String content;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
