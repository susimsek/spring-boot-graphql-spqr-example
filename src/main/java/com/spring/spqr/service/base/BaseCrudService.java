package com.spring.spqr.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseCrudService<R,W,ID> {

    Page<R> findAll(Pageable page);
    R getById(ID id);
    R save(W w);
    R update(ID id,W w);
    void deleteById(ID id);
}
