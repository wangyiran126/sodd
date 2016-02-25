package wangyiran.bean.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 25/2/2016.
 */
 class CacheInspect {
    private Map<Class,ClassDescription> cache;
    public ClassDescription lookup(Class beanClassName) {
        return cache.get(beanClassName);
    }

    public CacheInspect() {
        this.cache =  new HashMap<>();
    }
}
