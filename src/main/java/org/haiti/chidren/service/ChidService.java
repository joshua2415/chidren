package org.haiti.chidren.service;

import org.haiti.chidren.service.dto.ChidDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Chid.
 */
public interface ChidService {

    /**
     * Save a chid.
     *
     * @param chidDTO the entity to save
     * @return the persisted entity
     */
    ChidDTO save(ChidDTO chidDTO);

    /**
     * Get all the chids.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ChidDTO> findAll(Pageable pageable);


    /**
     * Get the "id" chid.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ChidDTO> findOne(Long id);

    /**
     * Delete the "id" chid.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
