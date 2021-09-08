package wiki.thin.common;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import wiki.thin.common.util.IdGenerateUtils;

import java.io.Serializable;

public class CustomIDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return IdGenerateUtils.getNextId();
    }
}
