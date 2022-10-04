package com.badaklng.app.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.badaklng.app.base.BaseModel;

/**
 * AppPositionListView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_POSITION_LKP_VIEW")

public class AppPositionLkpView extends BaseModel implements java.io.Serializable {

    // Fields
    private String positionId;
    private String posTitle;
    private Integer totalPerson;
    /*private String department;
    private String section;*/

    // Constructors
    /**
     * default constructor
     */
    public AppPositionLkpView() {
    }

    // Property accessors
    @Id

    @Column(name = "POSITION_ID", nullable = false, length = 10)

    public String getPositionId() {
        return this.positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    @Column(name = "POS_TITLE", length = 40)

    public String getPosTitle() {
        return this.posTitle;
    }

    public void setPosTitle(String posTitle) {
        this.posTitle = posTitle;
    }

    @Column(name = "TOTAL_PERSON")
    public Integer getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(Integer totalPerson) {
        this.totalPerson = totalPerson;
    }

   /* @Column(name = "DEPARTMENT")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "SECTION")
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }*/

}
