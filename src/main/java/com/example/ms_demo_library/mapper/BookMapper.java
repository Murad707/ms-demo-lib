package com.example.ms_demo_library.mapper;

import com.example.ms_demo_library.dao.entity.BookEntity;
import com.example.ms_demo_library.model.BookRequest;
import com.example.ms_demo_library.model.BookResponse;
import com.example.ms_demo_library.model.UpdateBookRequest;
import com.example.ms_demo_library.model.UpdateOneField;

import java.util.Objects;

public class BookMapper {

    public static BookEntity mapToEntity(BookRequest request){
        return BookEntity.builder()
                .title(request.getTitle())
                .publisher(request.getPublisher())
                .count(request.getCount())
                .build();
    }

    public static BookResponse mapResponseToEntity(BookEntity entity){
        return BookResponse.builder()
                .title(entity.getTitle())
                .publisher(entity.getPublisher())
                .count(entity.getCount())
                .build();
    }

    public static void updateBook(UpdateBookRequest request, BookEntity entity){
entity.setPublisher(request.getPublisher());
entity.setCount(request.getCount());
    }
    public static void updateCount(BookEntity entity,UpdateOneField oneField){
        entity.setPublisher(oneField.getPublisher());
    }

}
