package com.hurynovich.spring_security_demo.service;

import com.hurynovich.spring_security_demo.dto.AbstractDTO;

import java.util.List;

public interface DTOService<D extends AbstractDTO, I> {
    D create(final D d);

    D readById(final I id);

    List<D> readAll();

    D update(final D d);

    void delete(final I id);
}
