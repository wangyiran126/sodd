package wangyiran.bean.scope;

/**
 * Created by wangyiran on 25/2/2016.
 */
public interface Scope {
    public Object lookup(Class beanAClass);

    public void regist(Class beanClass, Object object);
}
