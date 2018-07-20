package org.haiti.chidren.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.haiti.chidren.service.ChidService;
import org.haiti.chidren.web.rest.errors.BadRequestAlertException;
import org.haiti.chidren.web.rest.util.HeaderUtil;
import org.haiti.chidren.web.rest.util.PaginationUtil;
import org.haiti.chidren.service.dto.ChidDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Chid.
 */
@RestController
@RequestMapping("/api")
public class ChidResource {

    private final Logger log = LoggerFactory.getLogger(ChidResource.class);

    private static final String ENTITY_NAME = "chid";

    private final ChidService chidService;

    public ChidResource(ChidService chidService) {
        this.chidService = chidService;
    }

    /**
     * POST  /chids : Create a new chid.
     *
     * @param chidDTO the chidDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chidDTO, or with status 400 (Bad Request) if the chid has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chids")
    @Timed
    public ResponseEntity<ChidDTO> createChid(@Valid @RequestBody ChidDTO chidDTO) throws URISyntaxException {
        log.debug("REST request to save Chid : {}", chidDTO);
        if (chidDTO.getId() != null) {
            throw new BadRequestAlertException("A new chid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChidDTO result = chidService.save(chidDTO);
        return ResponseEntity.created(new URI("/api/chids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chids : Updates an existing chid.
     *
     * @param chidDTO the chidDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chidDTO,
     * or with status 400 (Bad Request) if the chidDTO is not valid,
     * or with status 500 (Internal Server Error) if the chidDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chids")
    @Timed
    public ResponseEntity<ChidDTO> updateChid(@Valid @RequestBody ChidDTO chidDTO) throws URISyntaxException {
        log.debug("REST request to update Chid : {}", chidDTO);
        if (chidDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChidDTO result = chidService.save(chidDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chidDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chids : get all the chids.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chids in body
     */
    @GetMapping("/chids")
    @Timed
    public ResponseEntity<List<ChidDTO>> getAllChids(Pageable pageable) {
        log.debug("REST request to get a page of Chids");
        Page<ChidDTO> page = chidService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chids");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /chids/:id : get the "id" chid.
     *
     * @param id the id of the chidDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chidDTO, or with status 404 (Not Found)
     */
    @GetMapping("/chids/{id}")
    @Timed
    public ResponseEntity<ChidDTO> getChid(@PathVariable Long id) {
        log.debug("REST request to get Chid : {}", id);
        Optional<ChidDTO> chidDTO = chidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chidDTO);
    }

    /**
     * DELETE  /chids/:id : delete the "id" chid.
     *
     * @param id the id of the chidDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chids/{id}")
    @Timed
    public ResponseEntity<Void> deleteChid(@PathVariable Long id) {
        log.debug("REST request to delete Chid : {}", id);
        chidService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
