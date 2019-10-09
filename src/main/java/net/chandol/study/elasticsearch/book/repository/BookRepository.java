package net.chandol.study.elasticsearch.book.repository;

import net.chandol.study.elasticsearch.book.domain.Book;

import java.util.List;

public interface BookRepository {

    void save(Book book);

    List<Book> findByTitle(String title);
}
