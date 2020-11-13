package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.structure.ListDefinition;
import com.epam.university.java.project.core.cdi.structure.MapDefinition;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanFactoryImpl implements BeanFactory {

    public static final String SINGLETON_SCOPE = "singleton";

    private final BeanDefinitionRegistry beanDefinitionRegistry;
    private final Map<BeanDefinition, Object> singletonBeans = new HashMap<>();

    public BeanFactoryImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        return (T) getBean(beanClass.getName());
    }

    @Override
    public Object getBean(String beanName) {
        return getBean(beanDefinitionRegistry.getBeanDefinition(beanName));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return (T) getBean(beanName);
    }

    /**
     * Get Bean by BeanDefinition.
     *
     * @param definition information about bean from XML file.
     * @param <T>        Generic.
     * @return new Object necessary class from beanDefinition.
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(BeanDefinition definition) {
        for (BeanPropertyDefinition propertyDefinition : definition.getProperties()) {
            if (propertyDefinition.getValue() == null
                    && propertyDefinition.getRef() == null
                    && propertyDefinition.getData() == null) {
                throw new RuntimeException();
            }
        }

        try {
            T instance;
            Class<T> beanClass = (Class<T>) Class.forName(definition.getClassName());
            if (SINGLETON_SCOPE.equals(definition.getScope())
                    && singletonBeans.containsKey(definition)) {

                instance = (T) singletonBeans.get(definition);
                return instance;

            } else {
                instance = beanClass.getDeclaredConstructor().newInstance();

                if (SINGLETON_SCOPE.equals(definition.getScope())) {
                    singletonBeans.put(definition, instance);
                }
            }

            for (BeanPropertyDefinition property : definition.getProperties()) {
                Field beanField = beanClass.getDeclaredField(property.getName());
                beanField.setAccessible(true);

                if (property.getValue() != null) {
                    try {
                        beanField.set(instance, Integer.parseInt(property.getValue()));
                    } catch (Exception e) {
                        beanField.set(instance, property.getValue());
                    }
                }

                if (property.getRef() != null) {
                    Object object = getBean(property.getRef());
                    beanField.set(instance, object);
                }

                if (property.getData() == null) {
                    continue;
                }

                if (property.getData() instanceof ListDefinition) {
                    ListDefinition listDefinition = (ListDefinition) property.getData();

                    Collection<String> items =
                            listDefinition
                                    .getItems()
                                    .stream()
                                    .map(ListDefinition.ListItemDefinition::getValue)
                                    .collect(Collectors.toList());
                    beanField.set(instance, items);
                }

                if (property.getData() instanceof MapDefinition) {
                    MapDefinition mapDefinition = (MapDefinition) property.getData();

                    Map<String, Object> entries = new HashMap<>();

                    for (MapDefinition.MapEntryDefinition
                            entryDefinition : mapDefinition.getValues()) {

                        if (entryDefinition.getValue() != null
                                && entryDefinition.getRef() != null) {

                            throw new RuntimeException();
                        }

                        if (entryDefinition.getValue() != null) {
                            entries.put(entryDefinition.getKey(), entryDefinition.getValue());
                        }
                        if (entryDefinition.getRef() != null) {
                            Object object = getBean(entryDefinition.getRef());
                            entries.put(entryDefinition.getKey(), object);
                        }
                    }
                    beanField.set(instance, entries);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
