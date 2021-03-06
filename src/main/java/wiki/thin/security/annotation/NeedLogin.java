package wiki.thin.security.annotation;

import java.lang.annotation.*;

/**
 * need login annotation.
 *
 * @author Beldon
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NeedLogin {
}
