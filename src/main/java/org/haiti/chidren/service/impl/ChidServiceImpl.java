package org.haiti.chidren.service.impl;

import org.haiti.chidren.service.ChidService;
import org.haiti.chidren.domain.Chid;
import org.haiti.chidren.repository.ChidRepository;
import org.haiti.chidren.service.dto.ChidDTO;
import org.haiti.chidren.service.mapper.ChidMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Chid.
 */
@Service
@Transactional
public class ChidServiceImpl implements ChidService {

    private final Logger log = LoggerFactory.getLogger(ChidServiceImpl.class);

    private final ChidRepository chidRepository;

    private final ChidMapper chidMapper;

    public ChidServiceImpl(ChidRepository chidRepository, ChidMapper chidMapper) {
        this.chidRepository = chidRepository;
        this.chidMapper = chidMapper;
    }

    /**
     * Save a chid.
     *
     * @param chidDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChidDTO save(ChidDTO chidDTO) {
        log.debug("Request to save Chid : {}", chidDTO);
        Chid chid = chidMapper.toEntity(chidDTO);
        chid = chidRepository.save(chid);
        return chidMapper.toDto(chid);
    }

    /**
     * Get all the chids.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChidDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Chids");
        return chidRepository.findAll(pageable)
            .map(chidMapper::toDto);
    }


    /**
     * Get one chid by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChidDTO> findOne(Long id) {
        log.debug("Request to get Chid : {}", id);
        return chidRepository.findById(id)
            .map(chidMapper::toDto);
    }

    /**
     * Delete the chid by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chid : {}", id);
        chidRepository.deleteById(id);
    }
}
