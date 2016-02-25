package wangyiran.bean;

import wangyiran.bean.tool.ClassTool;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class BeanDefinition {
    Class beanClass;
    private String name;
    public Resolver resolver;
    public CtorInjectPoint ctorInjectPoint;
    private Type[] actualType;
    public BeanDefinition(Class beanAClass) {
        this.beanClass = beanAClass;
        this.name = beanAClass.getName();
        resolver = new Resolver();
    }


    public String getName() {
        return name;
    }


    public Class getBeanClass() {
        return beanClass;
    }

}
