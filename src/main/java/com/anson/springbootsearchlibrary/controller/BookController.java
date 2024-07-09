package com.anson.springbootsearchlibrary.controller;

import com.anson.springbootsearchlibrary.dto.BookRequest;
import com.anson.springbootsearchlibrary.model.Book;
import com.anson.springbootsearchlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // PathVariable 裡的參數要和url value一致
    // ResponseEntity 是 Spring Framework 中提供的一个类，用于封装 HTTP 响应，允许我们设置响应的状态码、头信息和响应体。它使得我们能够更加灵活和明确地控制 HTTP 响应。
    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {
        Book book = bookService.getBookById(bookId);

        if (book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // RequestBody 接收傳來的RequestBody --> POST
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        Integer bookId = bookService.createBook(bookRequest);

        Book book = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody BookRequest bookRequest) {
        // 檢查 Book 是否存在
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 修改 Book 數據
        bookService.updateBook(bookId, bookRequest);

        Book bookUpdated = bookService.getBookById(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(bookUpdated);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
