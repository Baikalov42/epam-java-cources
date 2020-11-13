package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "beans")
public class BeanDefinitionsContainer {

    @XmlElement(name = "bean", type = BeanDefinitionImpl.class)
    private Collection<BeanDefinition> beanDefinitions = new ArrayList<>();

    public Collection<BeanDefinition> getBeanDefinitions() {
        return  beanDefinitions;
    }

    public void setBeanDefinitions(Collection<BeanDefinition> beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
    }

    public int getBeansQuantity() {
        return beanDefinitions.size();
    }
}
