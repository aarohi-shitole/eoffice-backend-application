package com.techvg.vks.web.rest;

import com.techvg.vks.repository.MemberRepository;
import com.techvg.vks.service.MemberQueryService;
import com.techvg.vks.service.MemberService;
import com.techvg.vks.service.criteria.MemberCriteria;
import com.techvg.vks.service.dto.MemberDTO;
import com.techvg.vks.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.techvg.vks.domain.Member}.
 */
@RestController
@RequestMapping("/api")
public class MemberResource {

    private final Logger log = LoggerFactory.getLogger(MemberResource.class);

    private static final String ENTITY_NAME = "member";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final MemberQueryService memberQueryService;

    public MemberResource(MemberService memberService, MemberRepository memberRepository, MemberQueryService memberQueryService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.memberQueryService = memberQueryService;
    }

    /**
     * {@code POST  /members} : Create a new member.
     *
     * @param memberDTO the memberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberDTO, or with status {@code 400 (Bad Request)} if the member has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/members")
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberDTO memberDTO) throws URISyntaxException {
        log.debug("REST request to save Member : {}", memberDTO);
        if (memberDTO.getId() != null) {
            throw new BadRequestAlertException("A new member cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MemberDTO result = memberService.save(memberDTO);
        return ResponseEntity
            .created(new URI("/api/members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /members/:id} : Updates an existing member.
     *
     * @param id the id of the memberDTO to save.
     * @param memberDTO the memberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberDTO,
     * or with status {@code 400 (Bad Request)} if the memberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/members/{id}")
    public ResponseEntity<MemberDTO> updateMember(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MemberDTO memberDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Member : {}, {}", id, memberDTO);
        if (memberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MemberDTO result = memberService.update(memberDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /members/:id} : Partial updates given fields of an existing member, field will ignore if it is null
     *
     * @param id the id of the memberDTO to save.
     * @param memberDTO the memberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberDTO,
     * or with status {@code 400 (Bad Request)} if the memberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the memberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the memberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/members/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MemberDTO> partialUpdateMember(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MemberDTO memberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Member partially : {}, {}", id, memberDTO);
        if (memberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, memberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!memberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MemberDTO> result = memberService.partialUpdate(memberDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, memberDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /members} : get all the members.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of members in body.
     */
    @GetMapping("/members")
    public ResponseEntity<List<MemberDTO>> getAllMembers(
        MemberCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Members by criteria: {}", criteria.toString().replaceAll("[\n\r\t]", "_"));
        Page<MemberDTO> page = memberQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /members/count} : count all the members.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/members/count")
    public ResponseEntity<Long> countMembers(MemberCriteria criteria) {
        log.debug("REST request to count Members by criteria: {}", criteria.toString().replaceAll("[\n\r\t]", "_"));
        return ResponseEntity.ok().body(memberQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /members/:id} : get the "id" member.
     *
     * @param id the id of the memberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id) {
        log.debug("REST request to get Member : {}", id);
        Optional<MemberDTO> memberDTO = memberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memberDTO);
    }

    /**
     * {@code DELETE  /members/:id} : delete the "id" member.
     *
     * @param id the id of the memberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        log.debug("REST request to delete Member : {}", id);
        memberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
