package regist;

import wangyiran.bean.annotation.InjectMethod;

/**
 * Created by wangyiran on 29/2/2016.
 */
public class BeanC {
    private BeanA beanA;
    private BeanC beanC;

    @InjectMethod
    public void computeScore(BeanA beanA){
        this.beanA = beanA;
    }

    public BeanA getScore() {
        return beanA;
    }

    public BeanC getTotal() {
        return beanC;
    }

    public void setTotal(BeanC beanC) {
        this.beanC = beanC;
    }
}
