package wangyiran.bean;

import wangyiran.bean.point.CtorInjectPoint;
import wangyiran.bean.point.MethodInjectPoint;
import wangyiran.bean.resolve.Resolver;
import wangyiran.bean.scope.Scope;

import java.util.List;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class BeanDefinition {
    Class beanClass;
    private String name;
    public Resolver resolver;
    public CtorInjectPoint ctorInjectPoint;
    public List<MethodInjectPoint> methodInjectPoints;
    public Scope scope;
    public BeanDefinition(Class beanAClass, Scope scope) {
        this.beanClass = beanAClass;
        this.name = beanAClass.getName();
        resolver = new Resolver();
        this.scope = scope;
    }


    public String getName() {
        return name;
    }


    public Object scopelookup() {
        return scope.lookup(beanClass);
    }

    public void registScope(Object bean) {
        scope.regist(beanClass,bean);
    }
}
