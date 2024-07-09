package com.anson.springbootsearchlibrary.dao.impl;

import com.anson.springbootsearchlibrary.dao.BookDao;
import com.anson.springbootsearchlibrary.dto.BookRequest;
import com.anson.springbootsearchlibrary.model.Book;
import com.anson.springbootsearchlibrary.rowmapper.BookRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Book getBookById(Integer bookId) {
        // 注意SQL語法要用空格 SELECT column FROM table WHERE column = value
        String sql = "SELECT book_id, title, author, image_url, price, published_date, created_date, last_modified_date " +
                "FROM book WHERE book_id = :bookId";

        Map<String, Object> map = new HashMap<>();
        map.put("bookId", bookId);

        // SQL 回來轉成Java Object 透過RowMapper
        List<Book> bookList = namedParameterJdbcTemplate.query(sql, map, new BookRowMapper());

        if (bookList.size() > 0) {
            return bookList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer createBook(BookRequest bookRequest) {
        // SQL 語法 INSERT INTO table(column, column...) VALUES(value, value...)
        String sql = "INSERT INTO book(title, author, image_url, price, published_date, created_date, last_modified_date) " +
                "VALUES (:title, :author, :imageUrl, :price, :publishedDate, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("title", bookRequest.getTitle());
        map.put("author", bookRequest.getAuthor());
        map.put("imageUrl", bookRequest.getImageUrl());
        map.put("price", bookRequest.getPrice());
        map.put("publishedDate", bookRequest.getPublishedDate());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        // 自動新增Primary Key
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int bookId = keyHolder.getKey().intValue();

        return bookId;
    }

    @Override
    public void updateBook(Integer bookId, BookRequest bookRequest) {
        // SQL語法 UPDATE table SET column = value, column = value ... WHERE column = value
        String sql = "UPDATE book SET title = :title, author = :author, image_url = :imageUrl," +
                "price = :price, published_date = :publishedDate, last_modified_date = :lastModifiedDate " +
                "WHERE book_id =:bookId";

        Map<String, Object> map = new HashMap<>();
        map.put("bookId", bookId);
        map.put("title", bookRequest.getTitle());
        map.put("author", bookRequest.getAuthor());
        map.put("imageUrl", bookRequest.getImageUrl());
        map.put("price", bookRequest.getPrice());
        map.put("publishedDate", bookRequest.getPublishedDate());
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteBook(Integer bookId) {
        // SQL 語法 DELETE FROM table WHERE column = value
        String sql = "DELETE FROM book WHERE book_id =:bookId";

        Map<String, Object> map = new HashMap<>();
        map.put("bookId", bookId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
