package com.example.ms_demo_library.controller;

import com.example.ms_demo_library.model.BookRequest;
import com.example.ms_demo_library.model.BookResponse;
import com.example.ms_demo_library.model.UpdateBookRequest;
import com.example.ms_demo_library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/books")
public class LibraryController {
    private final LibraryService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBook(@RequestBody BookRequest request){
        service.createBook(request);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        service.deleteBook(id);
    }
    @DeleteMapping
    public void  deleteAllBooks(){
        service.deleteBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookByID(@PathVariable Long id){
        return service.getBookById(id);
    }
    @GetMapping
    public List<BookResponse>getBooks(){
       return service.getAllBooks();
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest request){
        service.updateBook(id,request);
    }
}
