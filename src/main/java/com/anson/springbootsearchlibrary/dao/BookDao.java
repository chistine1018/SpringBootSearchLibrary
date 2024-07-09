package com.anson.springbootsearchlibrary.dao;

import com.anson.springbootsearchlibrary.dto.BookRequest;
import com.anson.springbootsearchlibrary.model.Book;

public interface BookDao {

    Book getBookById(Integer bookId);

    Integer createBook(BookRequest bookRequest);

    void updateBook(Integer bookId, BookRequest bookRequest);

    void deleteBook(Integer bookId);
}
