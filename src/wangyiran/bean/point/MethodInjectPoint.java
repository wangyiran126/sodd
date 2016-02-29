package wangyiran.bean.point;

import java.lang.reflect.Method;

/**
 * Created by wangyiran on 29/2/2016.
 */
public class MethodInjectPoint {
    private Method method;
    private Class[] parameterTypes;
    public MethodInjectPoint(Method method, Class<?>[] parameterTypes) {
        this.method = method;
        this.parameterTypes = parameterTypes;
    }

    public Method getMethod() {
        return method;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
