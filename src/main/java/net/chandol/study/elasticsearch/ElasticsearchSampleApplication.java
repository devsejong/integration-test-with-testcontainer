package net.chandol.study.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import net.chandol.study.elasticsearch.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ElasticsearchSampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchSampleApplication.class, args);
    }

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
//        bookService.saveBook(new BookUpsertRequestDto(UUID.randomUUID().toString(), "test1", "author1"));
//        bookService.saveBook(new BookUpsertRequestDto(UUID.randomUUID().toString(), "test2", "author2"));
//        bookService.saveBook(new BookUpsertRequestDto(UUID.randomUUID().toString(), "test3", "author3"));
//
//        List<Book> books = bookService.findByTitle("test1");
//        books.forEach(book -> {
//            log.info("검색결과 : {}", book);
//        });
    }
}
