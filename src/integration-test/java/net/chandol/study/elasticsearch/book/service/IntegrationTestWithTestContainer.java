package net.chandol.study.elasticsearch.book.service;

import net.chandol.study.elasticsearch.book.domain.Book;
import net.chandol.study.elasticsearch.book.dto.BookUpsertRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTestWithTestContainer {
    @Autowired
    private BookService bookService;

    @Test
    public void 도서_저장_및_조회() {
        bookService.saveBook(new BookUpsertRequestDto("0", "JPA 프로그래밍", "김영한"));

        List<Book> jpa = bookService.findByTitle("JPA 프로그래밍");
        System.out.println(jpa);
    }

}
