package wangyiran.bean.exception;

/**
 * Created by wangyiran on 29/2/2016.
 */

public class BeanUncheckedException extends RuntimeException{
    public BeanUncheckedException(String message, Exception e) {
        super(message,e);
    }

    public BeanUncheckedException(String message) {
        super(message);
    }
}
