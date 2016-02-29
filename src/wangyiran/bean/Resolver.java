package wangyiran.bean;

/**
 * Created by wangyiran on 25/2/2016.
 */

import wangyiran.bean.annotation.InjectMethod;
import wangyiran.bean.factory.CtorInjectPointFactory;
import wangyiran.bean.point.CtorInjectPoint;
import wangyiran.bean.point.MethodInjectPoint;
import wangyiran.bean.tool.ConstructorResolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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


    public List<MethodInjectPoint> resolveMethodInjectPoint(Class beanClass) {
        //TODO 获取class的所有method
        Method[] methods= beanClass.getDeclaredMethods();
        List<MethodInjectPoint> methodInjectPoints = new ArrayList<>();
        //寻找所有满足注入的方法
        for (Method method : methods){
            if (method.getParameterTypes().length == 0){
                continue;
            }
            if (method.getName().startsWith("set")){
                continue;
            }
            InjectMethod injectMethod = method.getAnnotation(InjectMethod.class);
            if (injectMethod == null){
                continue;
            }
            methodInjectPoints.add(new MethodInjectPoint(method,method.getParameterTypes()));
        }

            return methodInjectPoints;
    }
}
