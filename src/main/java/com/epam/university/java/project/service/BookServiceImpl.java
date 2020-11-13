package com.epam.university.java.project.service;

import com.epam.university.java.project.core.cdi.impl.io.XmlResource;
import com.epam.university.java.project.core.state.machine.domain.StateMachineDefinition;
import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookEvent;
import com.epam.university.java.project.domain.BookStatus;

import java.time.LocalDate;
import java.util.Collection;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    private StateMachineManager stateMachineManager;
    private String contextPath;
    private StateMachineDefinition<BookStatus, BookEvent> stateMachineDefinition;
    private int countCreateBookInvocations = 0;


    @SuppressWarnings("unchecked")
    @Override
    public Book createBook() {
        if (countCreateBookInvocations == 0) {
            contextPath = getClass()
                    .getResource("/project/DefaultBookStateMachineDefinition.xml")
                    .getFile();

            stateMachineDefinition =
                    (StateMachineDefinition<BookStatus, BookEvent>) stateMachineManager
                            .loadDefinition(new XmlResource(contextPath));
        }
        countCreateBookInvocations++;
        Book book = bookDao.createBook();
        book.setStateMachineDefinition(stateMachineDefinition);
        stateMachineManager.initStateMachine(book, stateMachineDefinition);
        stateMachineManager.handleEvent(book, BookEvent.CREATE);
        return book;
    }

    @Override
    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    @Override
    public Collection<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void remove(Book book) {
        bookDao.remove(book);
    }

    @Override
    public Book save(Book book) {
        bookDao.save(book);
        return book;
    }

    @Override
    public Book accept(Book book, String number) {
        book.setSerialNumber(number);
        stateMachineManager.handleEvent(book, BookEvent.ACCEPT);
        return book;
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        book.setReturnDate(returnDate);
        stateMachineManager.handleEvent(book, BookEvent.ISSUE);
        return book;
    }

    @Override
    public Book returnFromIssue(Book book) {
        stateMachineManager.handleEvent(book, BookEvent.RETURN);
        return book;
    }
}