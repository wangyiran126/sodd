package wangyiran.bean.scope;

import wangyiran.bean.listener.RequestContextListener;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 26/2/2016.
 */
public class SessionScope implements Scope{
    private final String ATTR_NAME = SessionScope.class.getName();
    @Override
    public Object lookup(Class beanAClass) {
        HttpSession session =  getCurrentSession();
        Map map = (Map) session.getAttribute(ATTR_NAME);
        if (map == null){
            map = new HashMap<>();
            session.setAttribute(ATTR_NAME,map);
        }
        return map.get(beanAClass);
    }

    @Override
    public void regist(Class beanClass, Object bean) {
        //get request session
        HttpSession session = getCurrentSession();
        //put bean to beanMap of session
        Map map = (Map) session.getAttribute(ATTR_NAME);
        if (map == null){
            map = new HashMap<>();
            session.setAttribute(ATTR_NAME,map);
        }
        map.put(beanClass,bean);

    }

    public HttpSession getCurrentSession() {
        HttpServletRequest request = (HttpServletRequest) RequestContextListener.getCurrentRequest();
        return request.getSession();
    }
}
