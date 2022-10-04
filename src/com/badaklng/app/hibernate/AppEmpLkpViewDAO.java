package com.badaklng.app.hibernate;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badaklng.app.base.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for AppEmpLkpView entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.badaklng.app.hibernate.AppEmpLkpView
  * @author MyEclipse Persistence Tools 
 */
public class AppEmpLkpViewDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(AppEmpLkpViewDAO.class);
		//property constants
	public static final String NAME_DISPLAY = "nameDisplay";
	public static final String DEPT_ID = "deptId";
	public static final String DEPT_DESC = "deptDesc";
	public static final String SECTION_ID = "sectionId";
	public static final String SECTION_DESC = "sectionDesc";
	public static final String POSITION_NBR = "positionNbr";
	public static final String POSITION_DESC = "positionDesc";
	public static final String POS_REPORT_TO = "posReportTo";
	public static final String POS_REPORT_TO_DESC = "posReportToDesc";
	public static final String NAME_REPORT_TO = "nameReportTo";
	public static final String EMAIL = "email";
	public static final String EMPLID_REPORT_TO = "emplidReportTo";
	public static final String EMP_STATUS = "empStatus";
	public static final String EMP_STATUS_DESC = "empStatusDesc";
	public static final String COST_CENTER = "costCenter";



    
    public void save(AppEmpLkpView transientInstance) {
        log.debug("saving AppEmpLkpView instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(AppEmpLkpView persistentInstance) {
        log.debug("deleting AppEmpLkpView instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AppEmpLkpView findById( java.lang.String id) {
        log.debug("getting AppEmpLkpView instance with id: " + id);
        try {
            AppEmpLkpView instance = (AppEmpLkpView) getSession()
                    .get("com.badaklng.hris.hibernate.AppEmpLkpView", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<AppEmpLkpView> findByExample(AppEmpLkpView instance) {
        log.debug("finding AppEmpLkpView instance by example");
        try {
            List<AppEmpLkpView> results = (List<AppEmpLkpView>) getSession()
                    .createCriteria("com.badaklng.hris.hibernate.AppEmpLkpView")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding AppEmpLkpView instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from AppEmpLkpView as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<AppEmpLkpView> findByNameDisplay(Object nameDisplay
	) {
		return findByProperty(NAME_DISPLAY, nameDisplay
		);
	}
	
	public List<AppEmpLkpView> findByDeptId(Object deptId
	) {
		return findByProperty(DEPT_ID, deptId
		);
	}
	
	public List<AppEmpLkpView> findByDeptDesc(Object deptDesc
	) {
		return findByProperty(DEPT_DESC, deptDesc
		);
	}
	
	public List<AppEmpLkpView> findBySectionId(Object sectionId
	) {
		return findByProperty(SECTION_ID, sectionId
		);
	}
	
	public List<AppEmpLkpView> findBySectionDesc(Object sectionDesc
	) {
		return findByProperty(SECTION_DESC, sectionDesc
		);
	}
	
	public List<AppEmpLkpView> findByPositionNbr(Object positionNbr
	) {
		return findByProperty(POSITION_NBR, positionNbr
		);
	}
	
	public List<AppEmpLkpView> findByPositionDesc(Object positionDesc
	) {
		return findByProperty(POSITION_DESC, positionDesc
		);
	}
	
	public List<AppEmpLkpView> findByPosReportTo(Object posReportTo
	) {
		return findByProperty(POS_REPORT_TO, posReportTo
		);
	}
	
	public List<AppEmpLkpView> findByPosReportToDesc(Object posReportToDesc
	) {
		return findByProperty(POS_REPORT_TO_DESC, posReportToDesc
		);
	}
	
	public List<AppEmpLkpView> findByNameReportTo(Object nameReportTo
	) {
		return findByProperty(NAME_REPORT_TO, nameReportTo
		);
	}
	
	public List<AppEmpLkpView> findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List<AppEmpLkpView> findByEmplidReportTo(Object emplidReportTo
	) {
		return findByProperty(EMPLID_REPORT_TO, emplidReportTo
		);
	}
	
	public List<AppEmpLkpView> findByEmpStatus(Object empStatus
	) {
		return findByProperty(EMP_STATUS, empStatus
		);
	}
	
	public List<AppEmpLkpView> findByEmpStatusDesc(Object empStatusDesc
	) {
		return findByProperty(EMP_STATUS_DESC, empStatusDesc
		);
	}
	
	public List<AppEmpLkpView> findByCostCenter(Object costCenter
	) {
		return findByProperty(COST_CENTER, costCenter
		);
	}
	

	public List findAll() {
		log.debug("finding all AppEmpLkpView instances");
		try {
			String queryString = "from AppEmpLkpView";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public AppEmpLkpView merge(AppEmpLkpView detachedInstance) {
        log.debug("merging AppEmpLkpView instance");
        try {
            AppEmpLkpView result = (AppEmpLkpView) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(AppEmpLkpView instance) {
        log.debug("attaching dirty AppEmpLkpView instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AppEmpLkpView instance) {
        log.debug("attaching clean AppEmpLkpView instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}