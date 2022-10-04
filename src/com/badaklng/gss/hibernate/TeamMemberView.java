package com.badaklng.gss.hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TeamMemberView entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="TEAM_MEMBER_VIEW"
    ,schema="GSS"
)

public class TeamMemberView  implements java.io.Serializable {


    // Fields    

     private TeamMemberViewId id;


    // Constructors

    /** default constructor */
    public TeamMemberView() {
    }

    
    /** full constructor */
    public TeamMemberView(TeamMemberViewId id) {
        this.id = id;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="memberId", column=@Column(name="MEMBER_ID", nullable=false, length=16) ), 
        @AttributeOverride(name="teamId", column=@Column(name="TEAM_ID", nullable=false, length=8) ), 
        @AttributeOverride(name="assignBy", column=@Column(name="ASSIGN_BY", nullable=false, length=64) ), 
        @AttributeOverride(name="assignDate", column=@Column(name="ASSIGN_DATE", nullable=false, length=7) ) } )

    public TeamMemberViewId getId() {
        return this.id;
    }
    
    public void setId(TeamMemberViewId id) {
        this.id = id;
    }
   








}