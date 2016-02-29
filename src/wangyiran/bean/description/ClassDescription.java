package wangyiran.bean.description;

import wangyiran.bean.tool.Inspects;

import java.lang.reflect.Constructor;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class ClassDescription {
    private Class beanClass;
    private Constructor[] allCtors;
    private Integer usageCount = 0;

    public ClassDescription(Class beanClass) {
        this.beanClass = beanClass;
    }

    public static ClassDescription lookup(Class beanClass) {
        ClassDescription classDescription = Inspects.cacheInspect.lookup(beanClass);
        return classDescription;
    }

    public Constructor[] getAllCtors() {
        if (allCtors == null){
            allCtors = this.beanClass.getDeclaredConstructors();
        }
        return allCtors;
    }

    public void increase() {
        ++usageCount;
    }

    public Integer getUsageCount() {
        return usageCount;
    }
}
