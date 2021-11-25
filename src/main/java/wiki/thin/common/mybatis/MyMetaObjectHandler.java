package wiki.thin.common.mybatis;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.trace("start insert fill ....");
        this.setFieldValByName("createdBy", StpUtil.getLoginIdAsLong(), metaObject);
        this.setFieldValByName("lastModifiedBy", StpUtil.getLoginIdAsLong(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.trace("start update fill ....");
        this.setFieldValByName("lastModifiedBy", StpUtil.getLoginIdAsLong(), metaObject);
    }
}