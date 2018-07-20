package org.haiti.chidren.service.mapper;

import org.haiti.chidren.domain.*;
import org.haiti.chidren.service.dto.ChidDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Chid and its DTO ChidDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChidMapper extends EntityMapper<ChidDTO, Chid> {



    default Chid fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chid chid = new Chid();
        chid.setId(id);
        return chid;
    }
}
