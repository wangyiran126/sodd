import org.junit.Assert;
import org.junit.Test;
import regist.BeanA;
import regist.BeanB;
import wangyiran.bean.BeanDefinition;
import wangyiran.bean.BeanDefintionContainer;
import wangyiran.bean.scope.SingleScope;
import wangyiran.bean.tool.Inspects;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class TestRegister {
    @Test
    public void testRegist() throws Exception {
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
}
