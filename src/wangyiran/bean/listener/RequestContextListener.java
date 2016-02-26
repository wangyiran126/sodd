package wangyiran.bean.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by wangyiran on 26/2/2016.
 */
public class RequestContextListener implements ServletRequestListener {
    private static ThreadLocal<ServletRequest> threadLocal = new InheritableThreadLocal<>();
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        threadLocal.remove();
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest request = servletRequestEvent.getServletRequest();
        threadLocal.set(request);
    }

    public static ServletRequest getCurrentRequest(){
        return threadLocal.get();
    }
}
