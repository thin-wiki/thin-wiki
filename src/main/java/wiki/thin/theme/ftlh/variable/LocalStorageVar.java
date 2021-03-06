package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class LocalStorageVar extends BaseVariable {
    protected LocalStorageVar() {
        super("localStorageVar");
    }
}
