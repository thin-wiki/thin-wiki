package wiki.thin.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;
import wiki.thin.common.util.IdGenerateUtils;
import wiki.thin.entity.BaseEntity;

import java.util.Map;


/**
 * @author Beldon
 */
@Component
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
@Slf4j
public class MybatisIdInterceptor implements Interceptor {

    private static final int CHECK_ARGS_LENGTH = 2;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        final Object[] args = invocation.getArgs();

        if (args == null || args.length != CHECK_ARGS_LENGTH || !(args[0] instanceof MappedStatement) || (args[1] instanceof Map)) {
            return invocation.proceed();
        }

        final Object arg = args[1];
        if (isInsertSql(args[0]) && arg instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) arg;
            if (baseEntity.getId() == null) {
                baseEntity.setId(IdGenerateUtils.getNextId());
            }
        }

        return invocation.proceed();
    }

    private boolean isInsertSql(Object obj) {
        MappedStatement statement = (MappedStatement) obj;
        final SqlCommandType sqlCommandType = statement.getSqlCommandType();
        return SqlCommandType.INSERT.equals(sqlCommandType);
    }
}
