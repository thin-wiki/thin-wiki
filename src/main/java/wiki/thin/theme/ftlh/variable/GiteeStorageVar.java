package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class GiteeStorageVar extends BaseVariable {
    protected GiteeStorageVar() {
        super("giteeStorageVar");
    }
}
