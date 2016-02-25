package wangyiran.bean.tool;

import wangyiran.bean.ConstructorDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class ClassDescription {
    private Class beanClass;
    private ConstructorDescription[] allCtorDescription;
    private Ctors ctors;

    public ClassDescription(Class beanClass) {
        this.beanClass = beanClass;
    }

    public static ClassDescription lookup(Class beanClass) {
        ClassDescription classDescription = Inspects.cacheInspect.lookup(beanClass);
        if (classDescription == null){
            classDescription = new ClassDescription(beanClass);
        }
        return classDescription;
    }

}
