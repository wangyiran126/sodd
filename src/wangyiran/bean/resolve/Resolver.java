package wangyiran.bean.resolve;

/**
 * Created by wangyiran on 25/2/2016.
 */

import wangyiran.bean.factory.InjectPointFactory;
import wangyiran.bean.point.CtorInjectPoint;
import wangyiran.bean.point.MethodInjectPoint;

import java.util.List;

/**
 * hold all resolvers
 */
public class Resolver {
    private ConstructorResolver constructorResolver;
    private MethodResolver methodResolver;

    public Resolver() {
        this.constructorResolver = new ConstructorResolver(new InjectPointFactory());
        this.methodResolver = new MethodResolver(new InjectPointFactory());
    }

    public CtorInjectPoint createCtorInjectPoint(Class beanClass) {
        return constructorResolver.resolveCtorInjectPoint(beanClass);
    }


    public List<MethodInjectPoint> resolveMethodInjectPoint(Class beanClass) {
        return methodResolver.resolveMethodInjectPoint(beanClass);

    }
}
