package com.spring.spqr.service.impl;


import com.spring.spqr.domain.Book;
import com.spring.spqr.mapper.BookMapper;
import com.spring.spqr.model.request.BookRequestDto;
import com.spring.spqr.model.response.BookResponseDto;
import com.spring.spqr.repository.BookRepository;
import com.spring.spqr.service.BookService;
import com.spring.spqr.service.base.AbstractBaseCrudService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class BookServiceImpl extends AbstractBaseCrudService<BookResponseDto, BookRequestDto, Book,String> implements BookService {

    BookRepository bookRepository;
    BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        super(bookRepository,bookMapper);
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }
}

