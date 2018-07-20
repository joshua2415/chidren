package org.haiti.chidren.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import org.haiti.chidren.domain.enumeration.Sex;

import org.haiti.chidren.domain.enumeration.Currency;

/**
 * The Chid entity.
 */
@ApiModel(description = "The Chid entity.")
@Entity
@Table(name = "chid")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The fullName.
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "The fullName.", required = true)
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    /**
     * The sex.
     */
    @NotNull
    @ApiModelProperty(value = "The sex.", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    /**
     * The birthYear.
     */
    @NotNull
    @Min(value = 2000)
    @Max(value = 9999)
    @ApiModelProperty(value = "The birthYear.", required = true)
    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    /**
     * The entranceYear.
     */
    @Min(value = 2000)
    @Max(value = 9999)
    @ApiModelProperty(value = "The entranceYear.")
    @Column(name = "entrance_year")
    private Integer entranceYear;

    /**
     * The introduction.
     */
    @NotNull
    @Size(max = 1000)
    @ApiModelProperty(value = "The introduction.", required = true)
    @Column(name = "introduction", length = 1000, nullable = false)
    private String introduction;

    /**
     * The photoLink.
     */
    @NotNull
    @Size(max = 500)
    @ApiModelProperty(value = "The photoLink.", required = true)
    @Column(name = "photo_link", length = 500, nullable = false)
    private String photoLink;

    /**
     * The etc.
     */
    @Size(max = 1000)
    @ApiModelProperty(value = "The etc.")
    @Column(name = "etc", length = 1000)
    private String etc;

    /**
     * The supportedFund.
     */
    @Min(value = 0)
    @Max(value = 9999999)
    @ApiModelProperty(value = "The supportedFund.")
    @Column(name = "supported_fund")
    private Integer supportedFund;

    /**
     * The currency.
     */
    @NotNull
    @ApiModelProperty(value = "The currency.", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    /**
     * The modifyTime.
     */
    @ApiModelProperty(value = "The modifyTime.")
    @Column(name = "created_time")
    private ZonedDateTime createdTime;

    /**
     * The modifyTime.
     */
    @ApiModelProperty(value = "The modifyTime.")
    @Column(name = "modify_time")
    private ZonedDateTime modifyTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public Chid fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Sex getSex() {
        return sex;
    }

    public Chid sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Chid birthYear(Integer birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getEntranceYear() {
        return entranceYear;
    }

    public Chid entranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
        return this;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Chid introduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public Chid photoLink(String photoLink) {
        this.photoLink = photoLink;
        return this;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getEtc() {
        return etc;
    }

    public Chid etc(String etc) {
        this.etc = etc;
        return this;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public Integer getSupportedFund() {
        return supportedFund;
    }

    public Chid supportedFund(Integer supportedFund) {
        this.supportedFund = supportedFund;
        return this;
    }

    public void setSupportedFund(Integer supportedFund) {
        this.supportedFund = supportedFund;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Chid currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public Chid createdTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public ZonedDateTime getModifyTime() {
        return modifyTime;
    }

    public Chid modifyTime(ZonedDateTime modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public void setModifyTime(ZonedDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chid chid = (Chid) o;
        if (chid.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chid.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Chid{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", sex='" + getSex() + "'" +
            ", birthYear=" + getBirthYear() +
            ", entranceYear=" + getEntranceYear() +
            ", introduction='" + getIntroduction() + "'" +
            ", photoLink='" + getPhotoLink() + "'" +
            ", etc='" + getEtc() + "'" +
            ", supportedFund=" + getSupportedFund() +
            ", currency='" + getCurrency() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", modifyTime='" + getModifyTime() + "'" +
            "}";
    }
}
