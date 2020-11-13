package com.epam.university.java.project.core.cdi.bean;

import java.util.HashMap;
import java.util.Map;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> registry = new HashMap<>();

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        registry.put(definition.getId(), definition);
        registry.put(definition.getClassName(), definition);

        try {
            final Class<?> beanClass = Class.forName(definition.getClassName());
            for (Class<?> current : beanClass.getInterfaces()) {
                if (current != null) {
                    registry.put(current.getName(), definition);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return registry.get(beanId);
    }
}
