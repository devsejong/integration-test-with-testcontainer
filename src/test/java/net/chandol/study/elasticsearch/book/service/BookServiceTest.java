package net.chandol.study.elasticsearch.book.service;

import net.chandol.study.elasticsearch.book.domain.Book;
import net.chandol.study.elasticsearch.book.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    private BookService bookService;
    private BookRepository bookRepository;

    @Before
    public void setup(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void findByTitle() {
        // given
        Book book = new Book("0", "오브젝트", "조영호");
        Mockito.when(bookRepository.findByTitle(Mockito.anyString())).thenReturn(Arrays.asList(book));


        // when
        List<Book> books = bookService.findByTitle("??");

        // then
        Book resultBook = books.get(0);
        Assertions.assertThat(resultBook.getAuthor()).isEqualTo("조영호");
    }
}
