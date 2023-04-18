package com.example.ms_demo_library.service;

import com.example.ms_demo_library.client.StoreClient;
import com.example.ms_demo_library.dao.entity.BookEntity;
import com.example.ms_demo_library.dao.repository.LibraryRepository;
import com.example.ms_demo_library.mapper.BookMapper;
import com.example.ms_demo_library.model.BookRequest;
import com.example.ms_demo_library.model.BookResponse;
import com.example.ms_demo_library.model.UpdateBookRequest;
import com.example.ms_demo_library.model.UpdateOneField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ms_demo_library.mapper.BookMapper.mapToEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {
    private final LibraryRepository repository;
    private final StoreClient client;

    public void createBook(BookRequest request){
        log.info("ActionLog.createBook.start");

        client.getStocks(request.getRating())
     .stream()
     .filter(stock -> stock.getRating() > request.getRating())
     .findFirst()
     .orElseThrow(()-> new RuntimeException());

        repository.save(mapToEntity(request));
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
    public List<BookResponse> getAllBooks(){
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

    public void updateCount(Long id, UpdateOneField oneField){
       var count =  fetchBookIfExist(id);
       /*repository.findById(id).orElseThrow(() ->{
            log.error("ActionLog.updateCount.error id:{}",id);
            return new RuntimeException();
        });
        */
        BookMapper.updateCount(count,oneField);
    }

    private BookEntity fetchBookIfExist(Long id){
      return   repository.findById(id).
                orElseThrow(()->{
                    log.error("ActionLog.updateBook.error id:{}",id);
                    return new RuntimeException("Book not fount");
                });
    }
}
