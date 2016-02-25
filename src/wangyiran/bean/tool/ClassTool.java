package wangyiran.bean.tool;

import wangyiran.bean.BeanDefinition;
import wangyiran.bean.ConstructorDescription;
import wangyiran.bean.annotation.InjectConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class ClassTool {
    public static String getName(Class beanAClass) {
        return beanAClass.getName();
    }

}
