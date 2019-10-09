package net.chandol.study.elasticsearch.book.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chandol.study.elasticsearch.book.domain.Book;
import net.chandol.study.elasticsearch.book.dto.BookUpsertRequestDto;
import net.chandol.study.elasticsearch.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void saveBook(BookUpsertRequestDto requestDto) {
        Book book = new Book(requestDto.getId(), requestDto.getTitle(), requestDto.getAuthor());
        this.bookRepository.save(book);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
