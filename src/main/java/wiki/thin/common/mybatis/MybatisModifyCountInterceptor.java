package wiki.thin.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import static org.apache.ibatis.mapping.SqlCommandType.INSERT;
import static org.apache.ibatis.mapping.SqlCommandType.UPDATE;
import static org.apache.ibatis.mapping.SqlCommandType.DELETE;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author Beldon
 */
//@Component
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
@Slf4j
public class MybatisModifyCountInterceptor implements Interceptor {

    private static final int CHECK_LENGTH = 1;

    private final List<CountCallback> countCallbacks;

    public MybatisModifyCountInterceptor(List<CountCallback> countCallbacks) {
        this.countCallbacks = countCallbacks;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        if (CollectionUtils.isEmpty(countCallbacks)) {
            return invocation.proceed();
        }

        final Object[] args = invocation.getArgs();

        if (args != null && args.length > CHECK_LENGTH && args[0] instanceof MappedStatement) {
            final MappedStatement statement = (MappedStatement) args[0];
            final SqlCommandType commandType = statement.getSqlCommandType();
            boolean isModify = INSERT.equals(commandType) || UPDATE.equals(commandType) || DELETE.equals(commandType);
            if (isModify) {
                for (CountCallback countCallback : countCallbacks) {
                    countCallback.countModify(commandType);
                }
            }
        }

        return invocation.proceed();
    }

    public interface CountCallback {
        /**
         * 统计次数
         *
         * @param commandType commandType
         */
        @Async
        void countModify(SqlCommandType commandType);
    }
}
