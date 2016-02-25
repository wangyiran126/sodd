package wangyiran.bean.scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class SingleScope implements Scope{
    private Map<Class,Object> cache = new HashMap<>();
    public Object lookup(Class beanAClass) {
        return cache.get(beanAClass);
    }

    public void regist(Class beanClass, Object object) {
        cache.remove(beanClass);
        cache.put(beanClass,object);
    }
}
