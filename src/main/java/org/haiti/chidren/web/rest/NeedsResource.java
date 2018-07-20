package org.haiti.chidren.web.rest;

import java.util.List;

import org.haiti.chidren.security.AuthoritiesConstants;
import org.haiti.chidren.service.ChidService;
import org.haiti.chidren.service.dto.ChidDTO;
import org.haiti.chidren.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

/**
 * NeedsResource controller
 */
@RestController
@RequestMapping("/api/needs")
public class NeedsResource {

    private final Logger log = LoggerFactory.getLogger(NeedsResource.class);
    
    private final ChidService chidService;

    public NeedsResource(ChidService chidService) {
        this.chidService = chidService;
    }

    /**
     * GET  /chids : get all the chids.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chids in body
     */
    @GetMapping("/chids")
    @Timed
    @Secured(AuthoritiesConstants.ANONYMOUS)
    public ResponseEntity<List<ChidDTO>> getAllChids(Pageable pageable) {
        log.debug("REST request to get a page of Chids");
        Page<ChidDTO> page = chidService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chids");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
