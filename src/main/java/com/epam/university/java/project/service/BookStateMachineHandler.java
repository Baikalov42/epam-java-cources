package com.epam.university.java.project.service;

import com.epam.university.java.project.core.state.machine.domain.StateMachineEventHandler;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookStatus;

public class BookStateMachineHandler implements StateMachineEventHandler {

    public void create(Book book) {
        book.setState(BookStatus.DRAFT);
    }

    public void onAccept(Book book) {
        book.setState(BookStatus.ACCOUNTED);
    }

    public void onIssue(Book book) {
        book.setState(BookStatus.ISSUED);
    }

    public void onReturn(Book book) {
        book.setState(BookStatus.ACCOUNTED);
    }

}