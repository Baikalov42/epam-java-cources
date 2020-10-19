package com.epam.university.java.project.core.cdi.bean;

import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.util.Collection;

public class BeanDefinitionReaderImpl implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public BeanDefinitionReaderImpl(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.registry = beanDefinitionRegistry;
    }

    @Override
    public int loadBeanDefinitions(Resource resource) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BeanDefinitionsContainer.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            BeanDefinitionsContainer container =
                    (BeanDefinitionsContainer) jaxbUnmarshaller.unmarshal(resource.getFile());

            for (BeanDefinition beanDefinition : container.getBeans()) {
                registry.addBeanDefinition(beanDefinition);
            }
            return container.getBeans().size();

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }
}
