package exception;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wangyiran on 29/2/2016.
 */
public class TestException {
    public String testCatch(){
       try{
           int total = 4/0;
           return "test1";
       }catch (Exception e){
       }
        return "test2";
    }

    @Test
    public void testCatchReturn(){
        String test = testCatch();
        Assert.assertEquals("test2",test);
    }

    @Test
    public void testReturn() {
        try{
            int total = 4/0;
            System.out.println("test");
        }catch (Exception e){
        }
    }

    public String testRuntimeException(){
        try{
            int total = 4/0;
            return "test1";
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testRuntimeExceptionReturn(){
        String test = testRuntimeException();
        Assert.assertEquals("test1",test);
    }
}
