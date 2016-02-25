package regist;

import wangyiran.bean.annotation.InjectConstructor;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class BeanA {
    private BeanB beanB;

    @InjectConstructor
    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }

    public BeanB getBeanB(){
        return this.beanB;
    }
}
