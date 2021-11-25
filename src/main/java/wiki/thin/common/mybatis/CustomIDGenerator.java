package wiki.thin.common.mybatis;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;
import wiki.thin.common.util.IdGenerateUtils;

@Component
public class CustomIDGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return IdGenerateUtils.getNextId();
    }

}
