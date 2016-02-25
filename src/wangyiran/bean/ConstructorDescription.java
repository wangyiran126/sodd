package wangyiran.bean;

import java.lang.reflect.Constructor;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class ConstructorDescription {
    private Constructor constructor;
    private Class[] parameterTypes;
    public ConstructorDescription(Constructor constructor, Class[] parameterTypes) {
        this.constructor = constructor;
        this.parameterTypes = parameterTypes;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
