package net.chandol.study.elasticsearch.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookUpsertRequestDto {
    private String id;
    private String title;
    private String author;
}
