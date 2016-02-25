package wangyiran.bean;

/**
 * Created by wangyiran on 25/2/2016.
 */

import wangyiran.bean.annotation.InjectConstructor;
import wangyiran.bean.factory.CtorInjectPointFactory;
import wangyiran.bean.tool.ClassDescription;
import wangyiran.bean.tool.ConstructorResolver;
import wangyiran.bean.tool.InjectPointConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * hold all resolvers
 */
public class Resolver {
    private ConstructorResolver constructorResolver;

    public Resolver() {
        this.constructorResolver = new ConstructorResolver(new CtorInjectPointFactory());
    }

    public CtorInjectPoint createCtorInjectPoint(Class beanClass) {
        return constructorResolver.resolveCtorInjectPoint(beanClass);
    }


}
