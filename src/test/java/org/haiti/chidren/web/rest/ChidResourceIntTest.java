package org.haiti.chidren.web.rest;

import org.haiti.chidren.ChidrenApp;

import org.haiti.chidren.domain.Chid;
import org.haiti.chidren.repository.ChidRepository;
import org.haiti.chidren.service.ChidService;
import org.haiti.chidren.service.dto.ChidDTO;
import org.haiti.chidren.service.mapper.ChidMapper;
import org.haiti.chidren.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static org.haiti.chidren.web.rest.TestUtil.sameInstant;
import static org.haiti.chidren.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.haiti.chidren.domain.enumeration.Sex;
import org.haiti.chidren.domain.enumeration.Currency;
/**
 * Test class for the ChidResource REST controller.
 *
 * @see ChidResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChidrenApp.class)
public class ChidResourceIntTest {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final Sex DEFAULT_SEX = Sex.MALE;
    private static final Sex UPDATED_SEX = Sex.FEMALE;

    private static final Integer DEFAULT_BIRTH_YEAR = 2000;
    private static final Integer UPDATED_BIRTH_YEAR = 2001;

    private static final Integer DEFAULT_ENTRANCE_YEAR = 2000;
    private static final Integer UPDATED_ENTRANCE_YEAR = 2001;

    private static final String DEFAULT_INTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INTRODUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_PHOTO_LINK = "AAAAAAAAAA";
    private static final String UPDATED_PHOTO_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ETC = "AAAAAAAAAA";
    private static final String UPDATED_ETC = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUPPORTED_FUND = 0;
    private static final Integer UPDATED_SUPPORTED_FUND = 1;

    private static final Currency DEFAULT_CURRENCY = Currency.USD;
    private static final Currency UPDATED_CURRENCY = Currency.KRW;

    private static final ZonedDateTime DEFAULT_CREATED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_MODIFY_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFY_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ChidRepository chidRepository;


    @Autowired
    private ChidMapper chidMapper;
    

    @Autowired
    private ChidService chidService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChidMockMvc;

    private Chid chid;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChidResource chidResource = new ChidResource(chidService);
        this.restChidMockMvc = MockMvcBuilders.standaloneSetup(chidResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chid createEntity(EntityManager em) {
        Chid chid = new Chid()
            .fullName(DEFAULT_FULL_NAME)
            .sex(DEFAULT_SEX)
            .birthYear(DEFAULT_BIRTH_YEAR)
            .entranceYear(DEFAULT_ENTRANCE_YEAR)
            .introduction(DEFAULT_INTRODUCTION)
            .photoLink(DEFAULT_PHOTO_LINK)
            .etc(DEFAULT_ETC)
            .supportedFund(DEFAULT_SUPPORTED_FUND)
            .currency(DEFAULT_CURRENCY)
            .createdTime(DEFAULT_CREATED_TIME)
            .modifyTime(DEFAULT_MODIFY_TIME);
        return chid;
    }

    @Before
    public void initTest() {
        chid = createEntity(em);
    }

    @Test
    @Transactional
    public void createChid() throws Exception {
        int databaseSizeBeforeCreate = chidRepository.findAll().size();

        // Create the Chid
        ChidDTO chidDTO = chidMapper.toDto(chid);
        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isCreated());

        // Validate the Chid in the database
        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeCreate + 1);
        Chid testChid = chidList.get(chidList.size() - 1);
        assertThat(testChid.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testChid.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testChid.getBirthYear()).isEqualTo(DEFAULT_BIRTH_YEAR);
        assertThat(testChid.getEntranceYear()).isEqualTo(DEFAULT_ENTRANCE_YEAR);
        assertThat(testChid.getIntroduction()).isEqualTo(DEFAULT_INTRODUCTION);
        assertThat(testChid.getPhotoLink()).isEqualTo(DEFAULT_PHOTO_LINK);
        assertThat(testChid.getEtc()).isEqualTo(DEFAULT_ETC);
        assertThat(testChid.getSupportedFund()).isEqualTo(DEFAULT_SUPPORTED_FUND);
        assertThat(testChid.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testChid.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testChid.getModifyTime()).isEqualTo(DEFAULT_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void createChidWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chidRepository.findAll().size();

        // Create the Chid with an existing ID
        chid.setId(1L);
        ChidDTO chidDTO = chidMapper.toDto(chid);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chid in the database
        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = chidRepository.findAll().size();
        // set the field null
        chid.setFullName(null);

        // Create the Chid, which fails.
        ChidDTO chidDTO = chidMapper.toDto(chid);

        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSexIsRequired() throws Exception {
        int databaseSizeBeforeTest = chidRepository.findAll().size();
        // set the field null
        chid.setSex(null);

        // Create the Chid, which fails.
        ChidDTO chidDTO = chidMapper.toDto(chid);

        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBirthYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = chidRepository.findAll().size();
        // set the field null
        chid.setBirthYear(null);

        // Create the Chid, which fails.
        ChidDTO chidDTO = chidMapper.toDto(chid);

        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntroductionIsRequired() throws Exception {
        int databaseSizeBeforeTest = chidRepository.findAll().size();
        // set the field null
        chid.setIntroduction(null);

        // Create the Chid, which fails.
        ChidDTO chidDTO = chidMapper.toDto(chid);

        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhotoLinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = chidRepository.findAll().size();
        // set the field null
        chid.setPhotoLink(null);

        // Create the Chid, which fails.
        ChidDTO chidDTO = chidMapper.toDto(chid);

        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = chidRepository.findAll().size();
        // set the field null
        chid.setCurrency(null);

        // Create the Chid, which fails.
        ChidDTO chidDTO = chidMapper.toDto(chid);

        restChidMockMvc.perform(post("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChids() throws Exception {
        // Initialize the database
        chidRepository.saveAndFlush(chid);

        // Get all the chidList
        restChidMockMvc.perform(get("/api/chids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chid.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].birthYear").value(hasItem(DEFAULT_BIRTH_YEAR)))
            .andExpect(jsonPath("$.[*].entranceYear").value(hasItem(DEFAULT_ENTRANCE_YEAR)))
            .andExpect(jsonPath("$.[*].introduction").value(hasItem(DEFAULT_INTRODUCTION.toString())))
            .andExpect(jsonPath("$.[*].photoLink").value(hasItem(DEFAULT_PHOTO_LINK.toString())))
            .andExpect(jsonPath("$.[*].etc").value(hasItem(DEFAULT_ETC.toString())))
            .andExpect(jsonPath("$.[*].supportedFund").value(hasItem(DEFAULT_SUPPORTED_FUND)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(sameInstant(DEFAULT_CREATED_TIME))))
            .andExpect(jsonPath("$.[*].modifyTime").value(hasItem(sameInstant(DEFAULT_MODIFY_TIME))));
    }
    

    @Test
    @Transactional
    public void getChid() throws Exception {
        // Initialize the database
        chidRepository.saveAndFlush(chid);

        // Get the chid
        restChidMockMvc.perform(get("/api/chids/{id}", chid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chid.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.birthYear").value(DEFAULT_BIRTH_YEAR))
            .andExpect(jsonPath("$.entranceYear").value(DEFAULT_ENTRANCE_YEAR))
            .andExpect(jsonPath("$.introduction").value(DEFAULT_INTRODUCTION.toString()))
            .andExpect(jsonPath("$.photoLink").value(DEFAULT_PHOTO_LINK.toString()))
            .andExpect(jsonPath("$.etc").value(DEFAULT_ETC.toString()))
            .andExpect(jsonPath("$.supportedFund").value(DEFAULT_SUPPORTED_FUND))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.createdTime").value(sameInstant(DEFAULT_CREATED_TIME)))
            .andExpect(jsonPath("$.modifyTime").value(sameInstant(DEFAULT_MODIFY_TIME)));
    }
    @Test
    @Transactional
    public void getNonExistingChid() throws Exception {
        // Get the chid
        restChidMockMvc.perform(get("/api/chids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChid() throws Exception {
        // Initialize the database
        chidRepository.saveAndFlush(chid);

        int databaseSizeBeforeUpdate = chidRepository.findAll().size();

        // Update the chid
        Chid updatedChid = chidRepository.findById(chid.getId()).get();
        // Disconnect from session so that the updates on updatedChid are not directly saved in db
        em.detach(updatedChid);
        updatedChid
            .fullName(UPDATED_FULL_NAME)
            .sex(UPDATED_SEX)
            .birthYear(UPDATED_BIRTH_YEAR)
            .entranceYear(UPDATED_ENTRANCE_YEAR)
            .introduction(UPDATED_INTRODUCTION)
            .photoLink(UPDATED_PHOTO_LINK)
            .etc(UPDATED_ETC)
            .supportedFund(UPDATED_SUPPORTED_FUND)
            .currency(UPDATED_CURRENCY)
            .createdTime(UPDATED_CREATED_TIME)
            .modifyTime(UPDATED_MODIFY_TIME);
        ChidDTO chidDTO = chidMapper.toDto(updatedChid);

        restChidMockMvc.perform(put("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isOk());

        // Validate the Chid in the database
        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeUpdate);
        Chid testChid = chidList.get(chidList.size() - 1);
        assertThat(testChid.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testChid.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testChid.getBirthYear()).isEqualTo(UPDATED_BIRTH_YEAR);
        assertThat(testChid.getEntranceYear()).isEqualTo(UPDATED_ENTRANCE_YEAR);
        assertThat(testChid.getIntroduction()).isEqualTo(UPDATED_INTRODUCTION);
        assertThat(testChid.getPhotoLink()).isEqualTo(UPDATED_PHOTO_LINK);
        assertThat(testChid.getEtc()).isEqualTo(UPDATED_ETC);
        assertThat(testChid.getSupportedFund()).isEqualTo(UPDATED_SUPPORTED_FUND);
        assertThat(testChid.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testChid.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testChid.getModifyTime()).isEqualTo(UPDATED_MODIFY_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingChid() throws Exception {
        int databaseSizeBeforeUpdate = chidRepository.findAll().size();

        // Create the Chid
        ChidDTO chidDTO = chidMapper.toDto(chid);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restChidMockMvc.perform(put("/api/chids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chidDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Chid in the database
        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChid() throws Exception {
        // Initialize the database
        chidRepository.saveAndFlush(chid);

        int databaseSizeBeforeDelete = chidRepository.findAll().size();

        // Get the chid
        restChidMockMvc.perform(delete("/api/chids/{id}", chid.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Chid> chidList = chidRepository.findAll();
        assertThat(chidList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chid.class);
        Chid chid1 = new Chid();
        chid1.setId(1L);
        Chid chid2 = new Chid();
        chid2.setId(chid1.getId());
        assertThat(chid1).isEqualTo(chid2);
        chid2.setId(2L);
        assertThat(chid1).isNotEqualTo(chid2);
        chid1.setId(null);
        assertThat(chid1).isNotEqualTo(chid2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChidDTO.class);
        ChidDTO chidDTO1 = new ChidDTO();
        chidDTO1.setId(1L);
        ChidDTO chidDTO2 = new ChidDTO();
        assertThat(chidDTO1).isNotEqualTo(chidDTO2);
        chidDTO2.setId(chidDTO1.getId());
        assertThat(chidDTO1).isEqualTo(chidDTO2);
        chidDTO2.setId(2L);
        assertThat(chidDTO1).isNotEqualTo(chidDTO2);
        chidDTO1.setId(null);
        assertThat(chidDTO1).isNotEqualTo(chidDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chidMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chidMapper.fromId(null)).isNull();
    }
}
