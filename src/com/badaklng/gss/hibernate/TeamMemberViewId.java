package com.badaklng.gss.hibernate;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * TeamMemberViewId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class TeamMemberViewId  implements java.io.Serializable {


    // Fields    

     private String memberId;
     private String teamId;
     private String assignBy;
     private Timestamp assignDate;


    // Constructors

    /** default constructor */
    public TeamMemberViewId() {
    }

    
    /** full constructor */
    public TeamMemberViewId(String memberId, String teamId, String assignBy, Timestamp assignDate) {
        this.memberId = memberId;
        this.teamId = teamId;
        this.assignBy = assignBy;
        this.assignDate = assignDate;
    }

   
    // Property accessors

    @Column(name="MEMBER_ID", nullable=false, length=16)

    public String getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Column(name="TEAM_ID", nullable=false, length=8)

    public String getTeamId() {
        return this.teamId;
    }
    
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Column(name="ASSIGN_BY", nullable=false, length=64)

    public String getAssignBy() {
        return this.assignBy;
    }
    
    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }

    @Column(name="ASSIGN_DATE", nullable=false, length=7)

    public Timestamp getAssignDate() {
        return this.assignDate;
    }
    
    public void setAssignDate(Timestamp assignDate) {
        this.assignDate = assignDate;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TeamMemberViewId) ) return false;
		 TeamMemberViewId castOther = ( TeamMemberViewId ) other; 
         
		 return ( (this.getMemberId()==castOther.getMemberId()) || ( this.getMemberId()!=null && castOther.getMemberId()!=null && this.getMemberId().equals(castOther.getMemberId()) ) )
 && ( (this.getTeamId()==castOther.getTeamId()) || ( this.getTeamId()!=null && castOther.getTeamId()!=null && this.getTeamId().equals(castOther.getTeamId()) ) )
 && ( (this.getAssignBy()==castOther.getAssignBy()) || ( this.getAssignBy()!=null && castOther.getAssignBy()!=null && this.getAssignBy().equals(castOther.getAssignBy()) ) )
 && ( (this.getAssignDate()==castOther.getAssignDate()) || ( this.getAssignDate()!=null && castOther.getAssignDate()!=null && this.getAssignDate().equals(castOther.getAssignDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMemberId() == null ? 0 : this.getMemberId().hashCode() );
         result = 37 * result + ( getTeamId() == null ? 0 : this.getTeamId().hashCode() );
         result = 37 * result + ( getAssignBy() == null ? 0 : this.getAssignBy().hashCode() );
         result = 37 * result + ( getAssignDate() == null ? 0 : this.getAssignDate().hashCode() );
         return result;
   }   





}