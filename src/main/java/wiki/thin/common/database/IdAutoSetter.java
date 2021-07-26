package wiki.thin.common.database;

import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import wiki.thin.common.util.IdGenerateUtils;
import wiki.thin.entity.BaseEntity;

/**
 * @author Beldon
 */
@Component
public class IdAutoSetter implements BeforeConvertCallback<BaseEntity> {
    @Override
    public Publisher<BaseEntity> onBeforeConvert(BaseEntity entity, SqlIdentifier table) {
        if (entity.getId() == null) {
            entity.setId(IdGenerateUtils.getNextId());
            entity.markNew();
        }
        return Mono.just(entity);
    }

}
