package com.spring.spqr.mapper;


import com.spring.spqr.domain.Book;
import com.spring.spqr.mapper.base.BaseMapper;
import com.spring.spqr.model.request.BookRequestDto;
import com.spring.spqr.model.response.BookResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<BookResponseDto, BookRequestDto, Book> {
}
