package com.techvg.vks.repository;

import com.techvg.vks.domain.Society;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Society entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocietyRepository extends JpaRepository<Society, Long>, JpaSpecificationExecutor<Society> {}
