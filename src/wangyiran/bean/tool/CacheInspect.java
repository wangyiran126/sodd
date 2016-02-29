package wangyiran.bean.tool;

import wangyiran.bean.description.ClassDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 25/2/2016.
 */
 public class CacheInspect {
    private Map<Class,ClassDescription> cache;
    public ClassDescription lookup(Class beanClassName) {
        ClassDescription classDescription = cache.get(beanClassName);
        if (classDescription == null){
            classDescription = new ClassDescription(beanClassName);
            cache.put(beanClassName,classDescription);
        }
        classDescription.increase();
        return classDescription;

    }

    public CacheInspect() {
        this.cache =  new HashMap<>();
    }
}
