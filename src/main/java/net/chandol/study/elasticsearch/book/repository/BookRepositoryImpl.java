package net.chandol.study.elasticsearch.book.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.chandol.study.elasticsearch.book.domain.Book;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;

    @Override
    public void save(Book book) {
        try {
            UpdateRequest updateRequest = new UpdateRequest("book", "_doc", book.getId());
            updateRequest.docAsUpsert(true);

            String doc = objectMapper.writeValueAsString(book);

            updateRequest.doc(doc, XContentType.JSON);
            restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findByTitle(String title) {
        SearchSourceBuilder query = SearchSourceBuilder.searchSource().query(new MatchQueryBuilder("title", title));
        SearchRequest searchRequest = new SearchRequest("book");
        searchRequest.source(query);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();
            List<Book> books = new ArrayList<>();
            for (SearchHit hit : hits) {
                books.add(objectMapper.readValue(hit.getSourceAsString(), Book.class));
            }

            return books;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
