package net.chandol.study.elasticsearch.book.service;

import net.chandol.study.elasticsearch.book.domain.Book;
import net.chandol.study.elasticsearch.book.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static java.util.Arrays.asList;

public class BookServiceTest {
    private BookService bookService;
    private BookRepository bookRepository;

    @Before
    public void init(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void findByTitle() {
        // given
        Mockito.when(bookRepository.findByTitle(Mockito.any()))
                .thenReturn(asList(new Book("0", "제목", "작가")));

        // when

        List<Book> books = bookService.findByTitle("제목");


        // then
        Assertions.assertThat(books.get(0).getTitle()).isEqualTo("제목");

    }
}
