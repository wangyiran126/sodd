package wangyiran.bean.factory;

import wangyiran.bean.point.CtorInjectPoint;
import wangyiran.bean.point.MethodInjectPoint;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class InjectPointFactory {
    public CtorInjectPoint createInjectConstructor(Constructor annotationCtor, Class[] parameterTypes) {
        return new CtorInjectPoint(annotationCtor,parameterTypes);
    }

    public MethodInjectPoint createMethodInjectPoint(Method method, Class<?>[] parameterTypes) {
       return new MethodInjectPoint(method,method.getParameterTypes());
    }
}
