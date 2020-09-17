package com.epam.university.java.core.task018;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task018Impl implements Task018 {
    @Override
    public boolean isAnnotationPresent(Object toCheck, Class<?> annotationToFind) {

        if (annotationToFind == null || toCheck == null) {
            throw new IllegalArgumentException();
        }
        Class<?> inputClass = toCheck.getClass();

        List<Annotation> annotations = new ArrayList<>();

        Field[] fields = inputClass.getDeclaredFields();
        Method[] methods = inputClass.getDeclaredMethods();
        Constructor<?>[] constructors = inputClass.getConstructors();

        for (Constructor constructor : constructors) {
            annotations.addAll(Arrays.asList(constructor.getDeclaredAnnotations()));
        }
        for (Field field : fields) {
            annotations.addAll(Arrays.asList(field.getDeclaredAnnotations()));
        }
        for (Method method : methods) {
            annotations.addAll(Arrays.asList(method.getDeclaredAnnotations()));
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();

            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                annotations.addAll(Arrays.asList(parameterAnnotation)
                        .subList(0, parameterAnnotations.length));
            }
        }

        annotations.addAll(Arrays.asList(inputClass.getPackage().getDeclaredAnnotations()));
        annotations.addAll(Arrays.asList(inputClass.getDeclaredAnnotations()));

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationToFind)) {
                return true;
            }
        }
        return false;
    }
}
