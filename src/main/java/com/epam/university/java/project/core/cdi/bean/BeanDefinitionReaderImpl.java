package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {

    private final BeanDefinitionRegistry beanDefinitionRegistry;

    public BeanDefinitionReaderImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {

        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(BeanDefinitionsContainer.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            final BeanDefinitionsContainer container =
                    (BeanDefinitionsContainer) unmarshaller.unmarshal(resource.getFile());

            for (BeanDefinition beanDefinition : container.getBeanDefinitions()) {
                beanDefinitionRegistry.addBeanDefinition(beanDefinition);
            }
            return container.getBeansQuantity();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {

        int amount = 0;

        for (Resource resource : resources) {
            amount = amount + loadBeanDefinitions(resource);
        }
        return amount;
    }
}
