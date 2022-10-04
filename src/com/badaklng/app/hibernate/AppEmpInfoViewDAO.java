package com.badaklng.app.hibernate;

import java.sql.Timestamp;
import java.util.List;

//import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.badaklng.app.base.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * AppEmpInfoView entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.badaklng.app.hibernate.AppEmpInfoView
 * @author MyEclipse Persistence Tools
 */
public class AppEmpInfoViewDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AppEmpInfoViewDAO.class);
	// property constants
	public static final String PTM_ID = "ptmId";
	public static final String NAME_DISPLAY = "nameDisplay";
	public static final String DEPTID = "deptid";
	public static final String DEPT_DESC = "deptDesc";
	public static final String PB_SECTION = "pbSection";
	public static final String SECTION_DESC = "sectionDesc";
	public static final String POSITION_NBR = "positionNbr";
	public static final String POSITION_DESC = "positionDesc";
	public static final String POS_REPORT_TO = "posReportTo";
	public static final String POS_REPORT_TO_DESC = "posReportToDesc";
	public static final String NAME_REPORT_TO = "nameReportTo";
	public static final String SAL_GRADE = "salGrade";
	public static final String SAL_GRADE_ALPHA = "salGradeAlpha";
	public static final String PB_PTM_SAL_GRADE = "pbPtmSalGrade";
	public static final String JOBCODE = "jobcode";
	public static final String JOB_GRADE = "jobGrade";
	public static final String BIRTH_CITY_CD = "birthCityCd";
	public static final String BIRTH_CITY_DESC = "birthCityDesc";
	public static final String BIRTH_STATE_CD = "birthStateCd";
	public static final String BIRTH_STATE_DESC = "birthStateDesc";
	public static final String GENDER_DESC = "genderDesc";
	public static final String PAY_GROUP = "payGroup";
	public static final String TAX_MARITAL_STS = "taxMaritalSts";
	public static final String HIGHEST_EDUC_LVL = "highestEducLvl";
	public static final String FARTHEST_CITY = "farthestCity";
	public static final String RELIGION_DESC = "religionDesc";
	public static final String PB_ETHNIC_GROUP = "pbEthnicGroup";
	public static final String BLOOD_TYPE = "bloodType";
	public static final String PHONE_HOME = "phoneHome";
	public static final String PHONE_OFFICE = "phoneOffice";
	public static final String PHONE_HP = "phoneHp";
	public static final String PER_ORG = "perOrg";
	public static final String PB_EMP_CATEGORY = "pbEmpCategory";
	public static final String EMP_CATEGORY = "empCategory";
	public static final String LOCATION = "location";
	public static final String PB_SUB_LOCATION = "pbSubLocation";
	public static final String SUB_LOC = "subLoc";
	public static final String SHIFT = "shift";
	public static final String SHIFT_DESC = "shiftDesc";
	public static final String UNION_CD = "unionCd";
	public static final String HOUSING_TYPE = "housingType";
	public static final String ADDRESS = "address";
	public static final String ADDRESS_LONG = "addressLong";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String POSTAL = "postal";
	public static final String EMAIL = "email";
	public static final String EKI1 = "eki1";
	public static final String EKI1_CATEGORY = "eki1Category";
	public static final String EKI2 = "eki2";
	public static final String EKI2_CATEGORY = "eki2Category";
	public static final String EKI3 = "eki3";
	public static final String EKI3_CATEGORY = "eki3Category";
	public static final String EKI4 = "eki4";
	public static final String EKI4_CATEGORY = "eki4Category";
	public static final String EKI5 = "eki5";
	public static final String EKI5_CATEGORY = "eki5Category";
	public static final String SPOUSE_NM = "spouseNm";
	public static final String SPOUSE_BIRTH_CITY_CD = "spouseBirthCityCd";
	public static final String SPOUSE_BIRTH_CITY_DESC = "spouseBirthCityDesc";
	public static final String SPOUSE_BIRTH_STATE_CD = "spouseBirthStateCd";
	public static final String SPOUSE_BIRTH_STATE_DESC = "spouseBirthStateDesc";
	public static final String HIGHEST_EDUC_DESC = "highestEducDesc";
	public static final String JPM_PROFILE_ID = "jpmProfileId";
	public static final String EDUCATION = "education";
	public static final String NPWP = "npwp";
	public static final String PB_UPK = "pbUpk";
	public static final String UPOKPS = "upokps";
	public static final String PASSPORT_NO = "passportNo";
	public static final String PASSPORT_CITY = "passportCity";
	public static final String EMPLID_REPORT_TO = "emplidReportTo";
	public static final String EDUC_CD = "educCd";
	public static final String EDUC_SHORT = "educShort";
	public static final String EDUC_JURUSAN = "educJurusan";
	public static final String EDUC_NAME = "educName";
	public static final String EDUC_CITY = "educCity";
	public static final String ACTION = "action";
	public static final String ACTION_DESC = "actionDesc";
	public static final String REASON = "reason";
	public static final String REASON_DESC = "reasonDesc";
	public static final String EMP_STATUS = "empStatus";
	public static final String EMP_STATUS_DESC = "empStatusDesc";
	public static final String PAYROLL_STATUS = "payrollStatus";
	public static final String COST_CENTER = "costCenter";
	public static final String ORG_MAR_STS = "orgMarSts";
	public static final String EMPLOYEE_ID_ZP = "employeeIdZp";
	public static final String KTP = "ktp";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String ROSTER_ID = "rosterId";
	public static final String JOB_FAMILY = "jobFamily";
	public static final String JOB_FAM_DESC = "jobFamDesc";
	public static final String STATE_DESC = "stateDesc";
	public static final String ADDRESS_COUNTRY_CD = "addressCountryCd";
	public static final String TKI = "tki";
	public static final String PBA = "pba";

	public void save(AppEmpInfoView transientInstance) {
		log.debug("saving AppEmpInfoView instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppEmpInfoView persistentInstance) {
		log.debug("deleting AppEmpInfoView instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppEmpInfoView findById(java.lang.String id) {
		log.debug("getting AppEmpInfoView instance with id: " + id);
		try {
			AppEmpInfoView instance = (AppEmpInfoView) getSession().get("com.badaklng.hris.hibernate.AppEmpInfoView",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AppEmpInfoView> findByExample(AppEmpInfoView instance) {
		log.debug("finding AppEmpInfoView instance by example");
		try {
			List<AppEmpInfoView> results = (List<AppEmpInfoView>) getSession()
					.createCriteria("com.badaklng.hris.hibernate.AppEmpInfoView").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<AppEmpInfoView> findByProperty(String propertyName, Object value) {
//		log.debug("finding AppEmpInfoView instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from AppEmpInfoView as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AppEmpInfoView> findByPtmId(Object ptmId) {
		return findByProperty(PTM_ID, ptmId);
	}

	public List<AppEmpInfoView> findByNameDisplay(Object nameDisplay) {
		return findByProperty(NAME_DISPLAY, nameDisplay);
	}

	public List<AppEmpInfoView> findByDeptid(Object deptid) {
		return findByProperty(DEPTID, deptid);
	}

	public List<AppEmpInfoView> findByDeptDesc(Object deptDesc) {
		return findByProperty(DEPT_DESC, deptDesc);
	}

	public List<AppEmpInfoView> findByPbSection(Object pbSection) {
		return findByProperty(PB_SECTION, pbSection);
	}

	public List<AppEmpInfoView> findBySectionDesc(Object sectionDesc) {
		return findByProperty(SECTION_DESC, sectionDesc);
	}

	public List<AppEmpInfoView> findByPositionNbr(Object positionNbr) {
		return findByProperty(POSITION_NBR, positionNbr);
	}

	public List<AppEmpInfoView> findByPositionDesc(Object positionDesc) {
		return findByProperty(POSITION_DESC, positionDesc);
	}

	public List<AppEmpInfoView> findByPosReportTo(Object posReportTo) {
		return findByProperty(POS_REPORT_TO, posReportTo);
	}

	public List<AppEmpInfoView> findByPosReportToDesc(Object posReportToDesc) {
		return findByProperty(POS_REPORT_TO_DESC, posReportToDesc);
	}

	public List<AppEmpInfoView> findByNameReportTo(Object nameReportTo) {
		return findByProperty(NAME_REPORT_TO, nameReportTo);
	}

	public List<AppEmpInfoView> findBySalGrade(Object salGrade) {
		return findByProperty(SAL_GRADE, salGrade);
	}

	public List<AppEmpInfoView> findBySalGradeAlpha(Object salGradeAlpha) {
		return findByProperty(SAL_GRADE_ALPHA, salGradeAlpha);
	}

	public List<AppEmpInfoView> findByPbPtmSalGrade(Object pbPtmSalGrade) {
		return findByProperty(PB_PTM_SAL_GRADE, pbPtmSalGrade);
	}

	public List<AppEmpInfoView> findByJobcode(Object jobcode) {
		return findByProperty(JOBCODE, jobcode);
	}

	public List<AppEmpInfoView> findByJobGrade(Object jobGrade) {
		return findByProperty(JOB_GRADE, jobGrade);
	}

	public List<AppEmpInfoView> findByBirthCityCd(Object birthCityCd) {
		return findByProperty(BIRTH_CITY_CD, birthCityCd);
	}

	public List<AppEmpInfoView> findByBirthCityDesc(Object birthCityDesc) {
		return findByProperty(BIRTH_CITY_DESC, birthCityDesc);
	}

	public List<AppEmpInfoView> findByBirthStateCd(Object birthStateCd) {
		return findByProperty(BIRTH_STATE_CD, birthStateCd);
	}

	public List<AppEmpInfoView> findByBirthStateDesc(Object birthStateDesc) {
		return findByProperty(BIRTH_STATE_DESC, birthStateDesc);
	}

	public List<AppEmpInfoView> findByGenderDesc(Object genderDesc) {
		return findByProperty(GENDER_DESC, genderDesc);
	}

	public List<AppEmpInfoView> findByPayGroup(Object payGroup) {
		return findByProperty(PAY_GROUP, payGroup);
	}

	public List<AppEmpInfoView> findByTaxMaritalSts(Object taxMaritalSts) {
		return findByProperty(TAX_MARITAL_STS, taxMaritalSts);
	}

	public List<AppEmpInfoView> findByHighestEducLvl(Object highestEducLvl) {
		return findByProperty(HIGHEST_EDUC_LVL, highestEducLvl);
	}

	public List<AppEmpInfoView> findByFarthestCity(Object farthestCity) {
		return findByProperty(FARTHEST_CITY, farthestCity);
	}

	public List<AppEmpInfoView> findByReligionDesc(Object religionDesc) {
		return findByProperty(RELIGION_DESC, religionDesc);
	}

	public List<AppEmpInfoView> findByPbEthnicGroup(Object pbEthnicGroup) {
		return findByProperty(PB_ETHNIC_GROUP, pbEthnicGroup);
	}

	public List<AppEmpInfoView> findByBloodType(Object bloodType) {
		return findByProperty(BLOOD_TYPE, bloodType);
	}

	public List<AppEmpInfoView> findByPhoneHome(Object phoneHome) {
		return findByProperty(PHONE_HOME, phoneHome);
	}

	public List<AppEmpInfoView> findByPhoneOffice(Object phoneOffice) {
		return findByProperty(PHONE_OFFICE, phoneOffice);
	}

	public List<AppEmpInfoView> findByPhoneHp(Object phoneHp) {
		return findByProperty(PHONE_HP, phoneHp);
	}

	public List<AppEmpInfoView> findByPerOrg(Object perOrg) {
		return findByProperty(PER_ORG, perOrg);
	}

	public List<AppEmpInfoView> findByPbEmpCategory(Object pbEmpCategory) {
		return findByProperty(PB_EMP_CATEGORY, pbEmpCategory);
	}

	public List<AppEmpInfoView> findByEmpCategory(Object empCategory) {
		return findByProperty(EMP_CATEGORY, empCategory);
	}

	public List<AppEmpInfoView> findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List<AppEmpInfoView> findByPbSubLocation(Object pbSubLocation) {
		return findByProperty(PB_SUB_LOCATION, pbSubLocation);
	}

	public List<AppEmpInfoView> findBySubLoc(Object subLoc) {
		return findByProperty(SUB_LOC, subLoc);
	}

	public List<AppEmpInfoView> findByShift(Object shift) {
		return findByProperty(SHIFT, shift);
	}

	public List<AppEmpInfoView> findByShiftDesc(Object shiftDesc) {
		return findByProperty(SHIFT_DESC, shiftDesc);
	}

	public List<AppEmpInfoView> findByUnionCd(Object unionCd) {
		return findByProperty(UNION_CD, unionCd);
	}

	public List<AppEmpInfoView> findByHousingType(Object housingType) {
		return findByProperty(HOUSING_TYPE, housingType);
	}

	public List<AppEmpInfoView> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<AppEmpInfoView> findByAddressLong(Object addressLong) {
		return findByProperty(ADDRESS_LONG, addressLong);
	}

	public List<AppEmpInfoView> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<AppEmpInfoView> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<AppEmpInfoView> findByPostal(Object postal) {
		return findByProperty(POSTAL, postal);
	}

	public List<AppEmpInfoView> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<AppEmpInfoView> findByEki1(Object eki1) {
		return findByProperty(EKI1, eki1);
	}

	public List<AppEmpInfoView> findByEki1Category(Object eki1Category) {
		return findByProperty(EKI1_CATEGORY, eki1Category);
	}

	public List<AppEmpInfoView> findByEki2(Object eki2) {
		return findByProperty(EKI2, eki2);
	}

	public List<AppEmpInfoView> findByEki2Category(Object eki2Category) {
		return findByProperty(EKI2_CATEGORY, eki2Category);
	}

	public List<AppEmpInfoView> findByEki3(Object eki3) {
		return findByProperty(EKI3, eki3);
	}

	public List<AppEmpInfoView> findByEki3Category(Object eki3Category) {
		return findByProperty(EKI3_CATEGORY, eki3Category);
	}

	public List<AppEmpInfoView> findByEki4(Object eki4) {
		return findByProperty(EKI4, eki4);
	}

	public List<AppEmpInfoView> findByEki4Category(Object eki4Category) {
		return findByProperty(EKI4_CATEGORY, eki4Category);
	}

	public List<AppEmpInfoView> findByEki5(Object eki5) {
		return findByProperty(EKI5, eki5);
	}

	public List<AppEmpInfoView> findByEki5Category(Object eki5Category) {
		return findByProperty(EKI5_CATEGORY, eki5Category);
	}

	public List<AppEmpInfoView> findBySpouseNm(Object spouseNm) {
		return findByProperty(SPOUSE_NM, spouseNm);
	}

	public List<AppEmpInfoView> findBySpouseBirthCityCd(Object spouseBirthCityCd) {
		return findByProperty(SPOUSE_BIRTH_CITY_CD, spouseBirthCityCd);
	}

	public List<AppEmpInfoView> findBySpouseBirthCityDesc(Object spouseBirthCityDesc) {
		return findByProperty(SPOUSE_BIRTH_CITY_DESC, spouseBirthCityDesc);
	}

	public List<AppEmpInfoView> findBySpouseBirthStateCd(Object spouseBirthStateCd) {
		return findByProperty(SPOUSE_BIRTH_STATE_CD, spouseBirthStateCd);
	}

	public List<AppEmpInfoView> findBySpouseBirthStateDesc(Object spouseBirthStateDesc) {
		return findByProperty(SPOUSE_BIRTH_STATE_DESC, spouseBirthStateDesc);
	}

	public List<AppEmpInfoView> findByHighestEducDesc(Object highestEducDesc) {
		return findByProperty(HIGHEST_EDUC_DESC, highestEducDesc);
	}

	public List<AppEmpInfoView> findByJpmProfileId(Object jpmProfileId) {
		return findByProperty(JPM_PROFILE_ID, jpmProfileId);
	}

	public List<AppEmpInfoView> findByEducation(Object education) {
		return findByProperty(EDUCATION, education);
	}

	public List<AppEmpInfoView> findByNpwp(Object npwp) {
		return findByProperty(NPWP, npwp);
	}

	public List<AppEmpInfoView> findByPbUpk(Object pbUpk) {
		return findByProperty(PB_UPK, pbUpk);
	}

	public List<AppEmpInfoView> findByUpokps(Object upokps) {
		return findByProperty(UPOKPS, upokps);
	}

	public List<AppEmpInfoView> findByPassportNo(Object passportNo) {
		return findByProperty(PASSPORT_NO, passportNo);
	}

	public List<AppEmpInfoView> findByPassportCity(Object passportCity) {
		return findByProperty(PASSPORT_CITY, passportCity);
	}

	public List<AppEmpInfoView> findByEmplidReportTo(Object emplidReportTo) {
		return findByProperty(EMPLID_REPORT_TO, emplidReportTo);
	}

	public List<AppEmpInfoView> findByEducCd(Object educCd) {
		return findByProperty(EDUC_CD, educCd);
	}

	public List<AppEmpInfoView> findByEducShort(Object educShort) {
		return findByProperty(EDUC_SHORT, educShort);
	}

	public List<AppEmpInfoView> findByEducJurusan(Object educJurusan) {
		return findByProperty(EDUC_JURUSAN, educJurusan);
	}

	public List<AppEmpInfoView> findByEducName(Object educName) {
		return findByProperty(EDUC_NAME, educName);
	}

	public List<AppEmpInfoView> findByEducCity(Object educCity) {
		return findByProperty(EDUC_CITY, educCity);
	}

	public List<AppEmpInfoView> findByAction(Object action) {
		return findByProperty(ACTION, action);
	}

	public List<AppEmpInfoView> findByActionDesc(Object actionDesc) {
		return findByProperty(ACTION_DESC, actionDesc);
	}

	public List<AppEmpInfoView> findByReason(Object reason) {
		return findByProperty(REASON, reason);
	}

	public List<AppEmpInfoView> findByReasonDesc(Object reasonDesc) {
		return findByProperty(REASON_DESC, reasonDesc);
	}

	public List<AppEmpInfoView> findByEmpStatus(Object empStatus) {
		return findByProperty(EMP_STATUS, empStatus);
	}

	public List<AppEmpInfoView> findByEmpStatusDesc(Object empStatusDesc) {
		return findByProperty(EMP_STATUS_DESC, empStatusDesc);
	}

	public List<AppEmpInfoView> findByPayrollStatus(Object payrollStatus) {
		return findByProperty(PAYROLL_STATUS, payrollStatus);
	}

	public List<AppEmpInfoView> findByCostCenter(Object costCenter) {
		return findByProperty(COST_CENTER, costCenter);
	}

	public List<AppEmpInfoView> findByOrgMarSts(Object orgMarSts) {
		return findByProperty(ORG_MAR_STS, orgMarSts);
	}

	public List<AppEmpInfoView> findByEmployeeIdZp(Object employeeIdZp) {
		return findByProperty(EMPLOYEE_ID_ZP, employeeIdZp);
	}

	public List<AppEmpInfoView> findByKtp(Object ktp) {
		return findByProperty(KTP, ktp);
	}

	public List<AppEmpInfoView> findByScheduleId(Object scheduleId) {
		return findByProperty(SCHEDULE_ID, scheduleId);
	}

	public List<AppEmpInfoView> findByRosterId(Object rosterId) {
		return findByProperty(ROSTER_ID, rosterId);
	}

	public List<AppEmpInfoView> findByJobFamily(Object jobFamily) {
		return findByProperty(JOB_FAMILY, jobFamily);
	}

	public List<AppEmpInfoView> findByJobFamDesc(Object jobFamDesc) {
		return findByProperty(JOB_FAM_DESC, jobFamDesc);
	}

	public List<AppEmpInfoView> findByStateDesc(Object stateDesc) {
		return findByProperty(STATE_DESC, stateDesc);
	}

	public List<AppEmpInfoView> findByAddressCountryCd(Object addressCountryCd) {
		return findByProperty(ADDRESS_COUNTRY_CD, addressCountryCd);
	}

	public List<AppEmpInfoView> findByTki(Object tki) {
		return findByProperty(TKI, tki);
	}

	public List<AppEmpInfoView> findByPba(Object pba) {
		return findByProperty(PBA, pba);
	}

	public List findAll() {
		log.debug("finding all AppEmpInfoView instances");
		try {
			String queryString = "from AppEmpInfoView";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findSubordinate(String posId) {
		log.debug("finding subordinate "+posId);
		try {
//			String queryString = "from AppEmpInfoView as model where model.posReportTo= ? order by rand()";
//			Query queryObject = getSession().createQuery(queryString);
//			queryObject.setParameter(0, posId);
//			return queryObject.list();
			Criteria criteria = getSession().createCriteria(AppEmpInfoView.class);
			criteria.add(Restrictions.eq("posReportTo",posId));
			criteria.add(Restrictions.sqlRestriction("1=1 order by DBMS_RANDOM.VALUE"));
			return criteria.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertyRandom(String propertyName, Object value) {
		log.debug("finding AppEmpInfoView instance with property: " + propertyName + ", value: " + value);
		try {
//			String queryString = "from AppEmpInfoView as model where model." + propertyName + "= ? order by rand()";
//			Query queryObject = getSession().createQuery(queryString);
//			queryObject.setParameter(0, value);
//			return queryObject.list();
			Criteria criteria = getSession().createCriteria(AppEmpInfoView.class);
			criteria.add(Restrictions.eq(propertyName,value));
			criteria.add(Restrictions.sqlRestriction("1=1 order by DBMS_RANDOM.VALUE"));
			return criteria.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public AppEmpInfoView merge(AppEmpInfoView detachedInstance) {
		log.debug("merging AppEmpInfoView instance");
		try {
			AppEmpInfoView result = (AppEmpInfoView) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AppEmpInfoView> findByNamenya(String name){
		String find = "%" + name +"%";
		Criteria criteria = getSession().createCriteria(AppEmpInfoView.class);
		criteria.add(Restrictions.like("nameDisplay", find));
		return criteria.list();
	}

	public void attachDirty(AppEmpInfoView instance) {
		log.debug("attaching dirty AppEmpInfoView instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppEmpInfoView instance) {
		log.debug("attaching clean AppEmpInfoView instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}