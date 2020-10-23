package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;
import com.epam.university.java.project.core.cdi.structure.StructureDefinition;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {

    public static final String SINGLETON_SCOPE = "singleton";

    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private final Map<String, Object> singletonBeans = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {

        final BeanDefinition beanDefinition =
                beanDefinitionRegistry.getBeanDefinition(beanClass.getName());

        if (beanDefinition == null) {
            throw new RuntimeException("Bean definition not found, class: " + beanClass);
        }
        try {
            return getBean(beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Object getBean(String beanName) {
        final BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanName);

        if (beanDefinition == null) {
            throw new RuntimeException("Bean definition not found, name: " + beanName);
        }
        try {
            Object bean = getBean(beanDefinition);
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }

    @SuppressWarnings("unchecked")
    private <T> T getBean(BeanDefinition beanDefinition) throws Exception {

        final String beanId = beanDefinition.getId();
        final String beanClassName = beanDefinition.getClassName();

        final String postConstruct = (beanDefinition.getPostConstruct()
                == null ? "" : beanDefinition.getPostConstruct());

        final Class<T> beanClass = (Class<T>) Class.forName(beanClassName);
        final T bean = beanClass.newInstance();

        if (SINGLETON_SCOPE.equalsIgnoreCase(beanDefinition.getScope())) {
            if (singletonBeans.containsKey(beanId)) {
                return (T) singletonBeans.get(beanId);
            } else {
                singletonBeans.put(beanId, bean);
            }
        }

        for (BeanPropertyDefinition beanPropertyDefinition : beanDefinition.getProperties()) {

            final Field beanField = beanClass.getDeclaredField(beanPropertyDefinition.getName());
            beanField.setAccessible(true);

            final String value =
                    (beanPropertyDefinition.getValue()
                            == null ? "" : beanPropertyDefinition.getValue());

            final String ref =
                    (beanPropertyDefinition.getRef()
                            == null ? "" : beanPropertyDefinition.getRef());

            final StructureDefinition data = beanPropertyDefinition.getData();

            if (!value.trim().isEmpty()) {

                if (beanField.getType().isAssignableFrom(Integer.TYPE)) {
                    beanField.set(bean, Integer.parseInt(value));

                } else if (beanField.getType().isAssignableFrom(Long.TYPE)) {
                    beanField.set(bean, Long.parseLong(value));

                } else if (beanField.getType().isAssignableFrom(Float.TYPE)) {
                    beanField.set(bean, Float.parseFloat(value));

                } else if (beanField.getType().isAssignableFrom(Double.TYPE)) {
                    beanField.set(bean, Double.parseDouble(value));

                } else {
                    beanField.set(bean, value);
                }

            } else if (!ref.trim().isEmpty()) {
                beanField.set(bean, getBean(ref));

            } else if (data != null) {
                if (data instanceof ListDefinition) {
                    ListDefinition listDefinition = (ListDefinition) data;

                    List<Object> list = new ArrayList<>();
                    for (ListDefinition.ListItemDefinition item : listDefinition.getItems()) {
                        list.add(item.getValue());
                    }

                    beanField.set(bean, list);

                } else if (data instanceof MapDefinition) {

                    MapDefinition mapDefinition = (MapDefinition) data;
                    Map<Object, Object> map = new HashMap<>();

                    for (MapDefinition.MapEntryDefinition entry : mapDefinition.getValues()) {

                        final String eKey = entry.getKey();

                        final String eValue =
                                (entry.getValue() == null ? "" : entry.getValue());

                        final String eRef =
                                (entry.getRef() == null ? "" : entry.getRef());

                        if (!eValue.trim().isEmpty()) {
                            map.put(eKey, eValue);

                        } else if (!eRef.trim().isEmpty()) {
                            map.put(eKey, getBean(eRef));

                        } else {
                            throw new RuntimeException("Value not available "
                                    + beanDefinition.getClassName());
                        }
                    }
                    beanField.set(bean, map);
                } else {
                    throw new RuntimeException("Unsupported instance ");
                }
            } else {
                throw new RuntimeException("Value not available "
                        + beanDefinition.getClassName());
            }
        }
        if (!postConstruct.trim().isEmpty()) {
            beanClass.getDeclaredMethod(postConstruct).invoke(bean);
        }

        if (InitializingBean.class.isAssignableFrom(beanClass)) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        return bean;
    }
}