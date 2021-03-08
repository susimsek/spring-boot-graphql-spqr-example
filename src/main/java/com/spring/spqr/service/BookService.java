package com.spring.spqr.service;


import com.spring.spqr.model.request.BookRequestDto;
import com.spring.spqr.model.response.BookResponseDto;
import com.spring.spqr.service.base.BaseCrudService;

public interface BookService extends BaseCrudService<BookResponseDto, BookRequestDto,String> {
}
