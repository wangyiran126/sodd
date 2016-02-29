package wangyiran.bean.tool;

import wangyiran.bean.ConstructorDescription;
import wangyiran.bean.description.ClassDescription;
import wangyiran.bean.point.CtorInjectPoint;
import wangyiran.bean.annotation.InjectConstructor;
import wangyiran.bean.factory.CtorInjectPointFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyiran on 25/2/2016.
 */
public class ConstructorResolver {
    private CtorInjectPointFactory ctorInjectPointFactory;

    public ConstructorResolver(CtorInjectPointFactory ctorInjectPointFactory) {
        this.ctorInjectPointFactory = ctorInjectPointFactory;
    }

    public CtorInjectPoint resolveCtorInjectPoint(Class beanClass) {
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

        return ctorInjectPointFactory.createInjectConstructor(annotationCtor,annotationCtor.getParameterTypes());
    }

}
