package wangyiran.bean.resolve;

import wangyiran.bean.annotation.InjectMethod;
import wangyiran.bean.factory.InjectPointFactory;
import wangyiran.bean.point.MethodInjectPoint;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyiran on 29/2/2016.
 */
public class MethodResolver {
    private InjectPointFactory injectPointFactory;
    public MethodResolver(InjectPointFactory injectPointFactory) {
        this.injectPointFactory = injectPointFactory;
    }

    public List<MethodInjectPoint> resolveMethodInjectPoint(Class beanClass) {
        //获取class的所有method
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
            MethodInjectPoint methodInjectPoint = injectPointFactory.createMethodInjectPoint(method,method.getParameterTypes());
            methodInjectPoints.add(methodInjectPoint);
        }

        return methodInjectPoints;
    }
}
