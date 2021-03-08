package com.spring.spqr.model.response;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GenericPagedDto<T> extends PageImpl<T> {

    public GenericPagedDto(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public GenericPagedDto(List<T> content) {
        super(content);
    }
    
}
