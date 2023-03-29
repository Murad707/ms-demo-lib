package com.example.ms_demo_library.dao.repository;


import com.example.ms_demo_library.dao.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends JpaRepository<BookEntity,Long> {
}
