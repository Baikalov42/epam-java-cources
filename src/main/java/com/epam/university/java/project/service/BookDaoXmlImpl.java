package com.epam.university.java.project.service;

import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDaoXmlImpl implements BookDao {
    private List<Book> books = new ArrayList<>();
    private int id = 0;


    @Override
    public Book createBook() {
        id++;
        Book book = new BookImpl();
        book.setId(id);
        return book;
    }

    @Override
    public Book getBook(int id) {
        for (Book book : books) {
            if (book != null && book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Collection<Book> getBooks() {
        return books;
    }

    @Override
    public void remove(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, null);
            }
        }
    }

    @Override
    public Book save(Book book) {
        books.add(book);
        return book;
    }
}