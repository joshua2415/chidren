package org.haiti.chidren.repository;

import org.haiti.chidren.domain.Chid;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Chid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChidRepository extends JpaRepository<Chid, Long> {

}
