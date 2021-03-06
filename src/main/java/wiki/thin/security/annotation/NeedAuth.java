package wiki.thin.security.annotation;

import java.lang.annotation.*;

/**
 * @author Beldon
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@NeedLogin
public @interface NeedAuth {
}
