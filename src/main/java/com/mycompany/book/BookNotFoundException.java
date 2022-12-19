package com.mycompany.book;

public class BookNotFoundException extends Throwable {

    public static final String ERROR_MESSAGE_PREFIX = "Could not find book with id = ";

    public BookNotFoundException(int id) {
        super(ERROR_MESSAGE_PREFIX + id);
    }
}
