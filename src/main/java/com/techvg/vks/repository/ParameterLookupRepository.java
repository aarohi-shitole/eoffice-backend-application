package com.techvg.vks.repository;

import com.techvg.vks.domain.ParameterLookup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ParameterLookup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParameterLookupRepository extends JpaRepository<ParameterLookup, Long>, JpaSpecificationExecutor<ParameterLookup> {}
