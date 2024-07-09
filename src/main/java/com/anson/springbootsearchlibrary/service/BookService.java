package com.anson.springbootsearchlibrary.service;

import com.anson.springbootsearchlibrary.dto.BookRequest;
import com.anson.springbootsearchlibrary.model.Book;

public interface BookService {

    Book getBookById(Integer id);

    Integer createBook(BookRequest bookRequest);

    void updateBook(Integer bookId, BookRequest bookRequest);

    void deleteBook(Integer id);
}
