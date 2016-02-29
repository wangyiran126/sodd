package wangyiran.bean.factory;

import wangyiran.bean.point.CtorInjectPoint;

import java.lang.reflect.Constructor;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class CtorInjectPointFactory {
    public CtorInjectPoint createInjectConstructor(Constructor annotationCtor, Class[] parameterTypes) {
        return new CtorInjectPoint(annotationCtor,parameterTypes);
    }
}
