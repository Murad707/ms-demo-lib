package com.example.ms_demo_library.service;

import com.example.ms_demo_library.dao.entity.BookEntity;
import com.example.ms_demo_library.dao.repository.LibraryRepository;
import com.example.ms_demo_library.mapper.BookMapper;
import com.example.ms_demo_library.model.BookRequest;
import com.example.ms_demo_library.model.BookResponse;
import com.example.ms_demo_library.model.UpdateBookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {
    private final LibraryRepository repository;

    public void createBook(BookRequest request){
        log.info("ActionLog.createBook.start");

        repository.save(BookMapper.mapToEntity(request));
            log.info("ActionLog.createBook.success");
    }
    public void deleteBook(Long id){
        log.info("ActionLog.deleteBook.start :id {}",id);
        repository.deleteById(id);
        log.info("ActionLog.deleteBook.success :id {}",id);
    }
    public void deleteBooks(){
        log.info("ActionLog.deleteAll.start" );
        repository.deleteAll();
        log.info("ActionLog.deleteAll.success" );
    }

    public BookResponse getBookById(Long id){
        log.info("ActionLog.getBookByID.start id: {}",id );
       var book= fetchBookIfExist(id);

        log.info("ActionLog.getBookByID.success id: {}",id );
        return BookMapper.mapResponseToEntity(book);

    }
    public List<BookResponse>getAllBooks(){
        log.info("ActionLog.getBooks.start" );

     var books = repository.findAll().stream()
                .map(BookMapper::mapResponseToEntity)
                .collect(Collectors.toList());
        log.info("ActionLog.getBooks.success" );

        return books;
    }

    public void updateBook(Long id, UpdateBookRequest updateBookRequest){
        log.info("ActionLog.updateBook.start id: {}",id);

        var book= fetchBookIfExist(id);
BookMapper.updateBook(updateBookRequest,book);
         //  book.setPublisher(updateBookRequest.getPublisher());
           //book.setCount(updateBookRequest.getCount());
           repository.save(book);
           log.info("ActionLog.updateBook.success id: {}",id);
    }

    private BookEntity fetchBookIfExist(Long id){
      return   repository.findById(id).
                orElseThrow(()->{
                    log.error("ActionLog.updateBook.error id:{}",id);
                    return new RuntimeException("Book not fount");
                });
    }
}
