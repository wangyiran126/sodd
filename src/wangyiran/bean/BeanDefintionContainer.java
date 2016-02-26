package wangyiran.bean;

import wangyiran.bean.annotation.ScopeAnnotation;
import wangyiran.bean.scope.Scope;
import wangyiran.bean.scope.SingleScope;
import wangyiran.bean.tool.ClassTool;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class BeanDefintionContainer {
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    private Map<Class<? extends Scope>,Scope> scopes = new HashMap<>();
    public void regist(Class beanAClass, Class<? extends Scope> singleScope) {
        Scope scope = instantiateScope(beanAClass,singleScope);
        instantiateBeanDefinition(beanAClass,scope);
    }

    private void instantiateBeanDefinition(Class beanAClass, Scope scope) {
        BeanDefinition definition = new BeanDefinition(beanAClass,scope);
        beanDefinitionMap.put(definition.getName(),definition);
    }


    private  Scope instantiateScope(Class beanAClass, Class<? extends Scope> scopeClass) {
        Scope scope = scopes.get(scopeClass);
        if (scope != null){
            return scope;
        }
        if (scopeClass == null) {
            scopeClass = resolveScopeOfAnnotation(beanAClass);
            if (scopeClass == null){
                scopeClass =  SingleScope.class;
            }
        }
        try {
            scope = scopeClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        scopes.put(scopeClass,scope);
        return scope;
    }

    private Class<? extends Scope> resolveScopeOfAnnotation(Class beanAClass) {
        ScopeAnnotation scopeAnnotation = (ScopeAnnotation) beanAClass.getAnnotation(ScopeAnnotation.class);
        return scopeAnnotation != null ? scopeAnnotation.scope() : null;
    }

    public BeanDefinition getBeanDefinition(Class beanAClass) {
        String name = ClassTool.getName(beanAClass);
        return beanDefinitionMap.get(name);
    }

    public Object getBean(Class beanAClass) throws Exception {
        BeanDefinition definition =  getBeanDefinition(beanAClass);
        if (definition == null){
            throw new Exception(beanAClass+"没有regist");
        }
        Object bean = definition.scopelookup();
        if (bean != null){
            return bean;
        }
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
        Object bean = definition.ctorInjectPoint.constructor.newInstance(beanArgs);
        definition.registScope(bean);
        return bean;
    }

}
