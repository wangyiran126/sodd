package wangyiran.bean;

import wangyiran.bean.tool.ClassTool;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class BeanDefintionContainer {
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    public void regist(Class beanAClass) {
        BeanDefinition definition = new BeanDefinition(beanAClass);
        beanDefinitionMap.put(definition.getName(),definition);
    }

    public BeanDefinition getBeanDefinition(Class beanAClass) {
        String name = ClassTool.getName(beanAClass);
        return beanDefinitionMap.get(name);
    }

    public Object getBean(Class beanAClass) throws Exception {
       BeanDefinition definition =  getBeanDefinition(beanAClass);
        if (definition == null){
            return null;
        }else {
            return createBean(definition);
        }
    }

    private Object createBean(BeanDefinition definition) throws Exception {
        if (definition.ctorInjectPoint == null){
            definition.ctorInjectPoint = definition.resolver.createCtorInjectPoint(definition.beanClass);
        }
        Class[] references = definition.ctorInjectPoint.references;
        Object[] beanArgs = new Object[0];
        if (references != null && references.length > 0){
            beanArgs = new Object[references.length];
            for (int i = 0; i < references.length; i++) {
                Class reference = references[i];
                beanArgs[i] = getBean(reference);
                if (beanArgs[i] == null){
                    throw new Exception(reference+ "没有regist");
                }
            }
        }
        return definition.ctorInjectPoint.constructor.newInstance(beanArgs);
    }

}
