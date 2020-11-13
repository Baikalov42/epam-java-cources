package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.io.Resource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinitionImpl;
import com.epam.university.java.project.core.state.machine.domain.StateMachineState;
import com.epam.university.java.project.core.state.machine.domain.StatefulEntity;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class StateMachineManagerImpl implements StateMachineManager {
    StateMachineDefinition<BookStatus, BookEvent> stateMachineDefinition;
    BookStateMachineHandler handler;

    @Override
    public StateMachineDefinition<BookStatus, BookEvent> loadDefinition(Resource resource) {
        File file = resource.getFile();
        StateMachineDefinitionImpl definition = null;
        try {
            JAXBContext context = JAXBContext.newInstance(StateMachineDefinitionImpl.class);
            definition = (StateMachineDefinitionImpl) context
                    .createUnmarshaller().unmarshal(new FileReader(file));

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        stateMachineDefinition = definition;
        return definition;
    }

    @Override
    public <S, E> StatefulEntity<S, E> initStateMachine(StatefulEntity<S, E> entity,
                                                        StateMachineDefinition<S, E> definition) {
        entity.setStateMachineDefinition(definition);
        try {
            handler = (BookStateMachineHandler) stateMachineDefinition.getHandlerClass()
                    .getConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S, E> StatefulEntity<S, E> handleEvent(StatefulEntity<S, E> entity, E event) {
        Book book = (Book) entity;
        StateMachineState<BookStatus, BookEvent> state = null;
        Collection<StateMachineState<BookStatus, BookEvent>> states = stateMachineDefinition
                .getStates();
        if (event == BookEvent.CREATE) {
            handler.create(book);
        } else {
            for (StateMachineState<BookStatus, BookEvent> s : states) {
                if (event == s.getOn()) {
                    state = s;
                    break;
                }
            }
            Method[] declaredMethods = handler.getClass().getDeclaredMethods();
            for (Method m : declaredMethods) {
                assert state != null;
                if (m.getName().equals(state.getMethodToCall())) {
                    try {
                        m.invoke(handler, book);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

        }
        return (StatefulEntity<S, E>) book;
    }
}
