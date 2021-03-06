import org.junit.Assert;
import org.junit.Test;
import regist.BeanA;
import regist.BeanB;
import regist.BeanC;
import regist.session.SessionAnnoBean;
import regist.session.SessionBeanA;
import regist.utils.ServletsMockitoUtils;
import wangyiran.bean.BeanDefinition;
import wangyiran.bean.BeanDefintionContainer;
import wangyiran.bean.listener.RequestContextListener;
import wangyiran.bean.scope.SessionScope;
import wangyiran.bean.scope.SingleScope;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static regist.utils.ServletsMockitoUtils.createHttpRequest;
import static regist.utils.ServletsMockitoUtils.createSession;


/**
 * Created by wangyiran on 25/2/2016.
 */
public class TestRegister {
    @Test
    public void testRegist(){
        BeanDefintionContainer container = new BeanDefintionContainer();
        container.regist(BeanA.class, SingleScope.class);
        container.regist(BeanB.class,null);
        BeanDefinition definition = container.getBeanDefinition(BeanA.class);
        Assert.assertNotNull(definition);
        BeanA beanA = (BeanA) container.getBean(BeanA.class);
        Assert.assertNotNull(beanA);
        Assert.assertNotNull(beanA.getBeanB());
        BeanA beanA2 = (BeanA) container.getBean(BeanA.class);

    }

    @Test
    public void testMockSession(){
        //simulate session
        HttpSession httpSession = createSession();
        httpSession.setAttribute("a","b");
        Assert.assertEquals("b",httpSession.getAttribute("a"));
        HttpServletRequest httpRequest = createHttpRequest(httpSession);
        ServletRequestEvent event =ServletsMockitoUtils.createServletRequestEvent(httpRequest);
        Assert.assertNotNull(httpRequest);
        Assert.assertNotNull(httpRequest.getSession());
        Assert.assertEquals("b",httpRequest.getSession().getAttribute("a"));
        RequestContextListener requestContextListener = new RequestContextListener();
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        Assert.assertNotNull(request);
        Assert.assertEquals("b",request.getSession().getAttribute("a"));

        requestContextListener.requestInitialized(event);

    }

    @Test
    public void testSessionScope(){
        HttpSession httpSession = createSession();
        HttpServletRequest httpRequest = createHttpRequest(httpSession);
        ServletRequestEvent event =ServletsMockitoUtils.createServletRequestEvent(httpRequest);
        RequestContextListener requestContextListener = new RequestContextListener();
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        requestContextListener.requestInitialized(event);
        BeanDefintionContainer container = new BeanDefintionContainer();
        container.regist(SessionBeanA.class, SessionScope.class);
        SessionBeanA beanA = (SessionBeanA) container.getBean(SessionBeanA.class);
        Assert.assertNotNull(beanA);
        SessionBeanA beanA2 = (SessionBeanA) container.getBean(SessionBeanA.class);
        Assert.assertEquals(beanA,beanA2);
    }

    @Test
    public void testAnnotationScope() {
        HttpSession httpSession = createSession();
        HttpServletRequest httpRequest = createHttpRequest(httpSession);
        ServletRequestEvent event =ServletsMockitoUtils.createServletRequestEvent(httpRequest);
        RequestContextListener requestContextListener = new RequestContextListener();
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        requestContextListener.requestInitialized(event);
        BeanDefintionContainer container = new BeanDefintionContainer();

        container.regist(SessionAnnoBean.class, null);
        SessionAnnoBean beanA = (SessionAnnoBean) container.getBean(SessionAnnoBean.class);
        Assert.assertNotNull(beanA);
        SessionAnnoBean beanA2 = (SessionAnnoBean) container.getBean(SessionAnnoBean.class);
        Assert.assertEquals(beanA,beanA2);
    }

    @Test
    public void testMethodInject(){
        BeanDefintionContainer container = new BeanDefintionContainer();
        container.regist(BeanC.class,null);
        container.regist(BeanA.class,null);
        container.regist(BeanB.class,null);

        BeanC beanC = (BeanC) container.getBean(BeanC.class);
        Assert.assertNotNull(beanC);
        Assert.assertTrue(beanC.getScore() != null);
        Assert.assertTrue(beanC.getTotal() == null );
    }


}
