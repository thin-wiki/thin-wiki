package wiki.thin.security;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author beldon
 */
@WebListener
@Component
public class OnlineUserManager implements HttpSessionListener {
    private static final Map<String, HttpSession> ONLINE_SESSION_MAP = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        final HttpSession session = se.getSession();
        ONLINE_SESSION_MAP.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ONLINE_SESSION_MAP.remove(se.getSession().getId());
    }

    /**
     * 清空所有 session 内容
     */
    public void clearAll() {
        for (HttpSession session : ONLINE_SESSION_MAP.values()) {
            session.invalidate();
        }
    }
}
