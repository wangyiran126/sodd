package wangyiran.bean.point;

import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class CtorInjectPoint {
    public Constructor constructor;
    public Class[] references;

    public CtorInjectPoint(Constructor annotationCtor, Class[] typeParameters) {
        this.constructor = annotationCtor;
        this.references = typeParameters;
    }
}
