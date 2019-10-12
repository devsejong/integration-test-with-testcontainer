package net.chandol.study.elasticsearch.book.service;

import net.chandol.study.elasticsearch.book.domain.Book;
import net.chandol.study.elasticsearch.book.dto.BookUpsertRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookServiceIntegrationTest {
    @Autowired
    private BookService bookService;

    @Before
    public void init() {
        bookService.saveBook(new BookUpsertRequestDto("0", "제목", "작가"));
    }

    @Test
    public void findByTitle() {
        // given
        // when
        List<Book> books = bookService.findByTitle("제목");

        // then
        assertThat(books.get(0).getTitle()).isEqualTo("제목");
    }
}
