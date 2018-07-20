package org.haiti.chidren.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import org.haiti.chidren.domain.enumeration.Sex;
import org.haiti.chidren.domain.enumeration.Currency;

/**
 * A DTO for the Chid entity.
 */
public class ChidDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String fullName;

    @NotNull
    private Sex sex;

    @NotNull
    @Min(value = 2000)
    @Max(value = 9999)
    private Integer birthYear;

    @Min(value = 2000)
    @Max(value = 9999)
    private Integer entranceYear;

    @NotNull
    @Size(max = 1000)
    private String introduction;

    @NotNull
    @Size(max = 500)
    private String photoLink;

    @Size(max = 1000)
    private String etc;

    @Min(value = 0)
    @Max(value = 9999999)
    private Integer supportedFund;

    @NotNull
    private Currency currency;

    private ZonedDateTime createdTime;

    private ZonedDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(Integer entranceYear) {
        this.entranceYear = entranceYear;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public Integer getSupportedFund() {
        return supportedFund;
    }

    public void setSupportedFund(Integer supportedFund) {
        this.supportedFund = supportedFund;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public ZonedDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(ZonedDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChidDTO chidDTO = (ChidDTO) o;
        if (chidDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chidDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChidDTO{" +
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
