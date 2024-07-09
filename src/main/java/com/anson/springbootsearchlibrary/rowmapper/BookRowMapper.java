package com.anson.springbootsearchlibrary.rowmapper;

import com.anson.springbootsearchlibrary.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookRowMapper implements RowMapper<Book> {


    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        // column name
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setImageUrl(rs.getString("image_url"));
        book.setPrice(rs.getInt("price"));
        book.setPublishDate(rs.getDate("published_date"));
        book.setCreateDate(rs.getDate("created_date"));
        book.setLastModifiedDate(rs.getDate("last_modified_date"));

        return book;
    }
}
