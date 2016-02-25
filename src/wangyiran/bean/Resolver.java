package wangyiran.bean;

/**
 * Created by wangyiran on 25/2/2016.
 */

import wangyiran.bean.annotation.InjectConstructor;
import wangyiran.bean.tool.ClassDescription;
import wangyiran.bean.tool.InjectPointConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有resolver集合
 */
public class Resolver {
    public CtorInjectPoint createCtorInjectPoint(Class beanClass) {
        ClassDescription classDescription = ClassDescription.lookup(beanClass);
        Constructor[] constructors = classDescription.getAllCtors();
        List<ConstructorDescription> constructorDescriptions = new ArrayList<>(constructors.length);
        Constructor annotationCtor = null;//注解的构造器
        Constructor defaultCtor = null;
        for (Constructor constructor : constructors){
            Class[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 0){
                defaultCtor = constructor;
            }
            Annotation injectAnnotation = constructor.getAnnotation(InjectConstructor.class);
            if (injectAnnotation != null){
                annotationCtor = constructor;
            }
            ConstructorDescription constructorDescription = new ConstructorDescription(constructor,parameterTypes);
            constructorDescriptions.add(constructorDescription);
        }
        if (annotationCtor == null){
            annotationCtor = defaultCtor;
        }

        return createInjectConstructor(annotationCtor,annotationCtor.getParameterTypes());
    }

    private CtorInjectPoint createInjectConstructor(Constructor annotationCtor, Class[] parameterTypes) {
        return new CtorInjectPoint(annotationCtor,parameterTypes);
    }
}
