package com.hurynovich.spring_security_demo.converter;

import com.hurynovich.spring_security_demo.dto.AbstractDTO;
import com.hurynovich.spring_security_demo.entity.AbstractEntity;

public interface DTOEntityConverter<D extends AbstractDTO, E extends AbstractEntity> {
    D convert(final E e);

    E convert(final D d);
}
