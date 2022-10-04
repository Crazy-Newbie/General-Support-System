package com.badaklng.app.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.JDBCException;
import org.springframework.validation.FieldError;

import com.badaklng.app.constant.Constants;
import com.badaklng.app.constant.FormModifierEnum;
import com.badaklng.app.constant.MessageTypeEnum;
import com.badaklng.app.model.Message;
import com.badaklng.app.utilities.ExceptionTokenizer;
import com.badaklng.app.utilities.FormActionEnum;
import com.badaklng.app.utilities.Utility;

public class BaseForm {

	protected Object assertionKey; // Generic implementation for assertion key,
	// can holds single or multiple values

	protected String formCode;

	protected String action;
//	protected String docAction;

	protected FormModifierEnum formModifier;
//	protected FormActionEnum formAction;

	protected List<Message> messages = new ArrayList<Message>(0);

	protected String criteria;

	// YOU CAN ACTIVATE THIS ATTRIBUTE IF YOU NEED TO
//	protected SortColumn selectedSortColumn = new SortColumn();
//	protected List<SortColumn> sortColumns = new ArrayList<SortColumn>(0);
//	protected List<SortColumn> availableSortColumns = new ArrayList<SortColumn>(0);

	// addition
	protected String keyword;
	protected Integer accessCode;
	protected Boolean isLookup;
	protected Integer limit;
	protected String redirectURL;
	protected Boolean isSubmitted;

	// addition for DataTables
	protected Integer draw;
	protected Integer start;
	protected Integer length;
	// protected Search search;
	// protected Map<String, Object> search = new HashMap<String, Object>();
	protected HashMap<Integer, Map<String, Object>> order = new HashMap<Integer, Map<String, Object>>();

	protected String orderBy;
	protected String orderType;

	// addition for mobile
//	protected String userId;
//	protected String token;
//	protected String imei;
//	
//	protected String pageRequested;
//	protected PageGenerator paging;

	public HashMap<Integer, Map<String, Object>> getOrder() {
		return order;
	}

	public void setOrder(HashMap<Integer, Map<String, Object>> order) {
		this.order = order;
	}

//	public String getPageRequested() {
//		return pageRequested;
//	}
//
//	public void setPageRequested(String pageRequested) {
//		this.pageRequested = pageRequested;
//	}
//	
//	public PageGenerator getPageGenerator() {
//		return paging;
//	}

//	public void addAvailableSortColumn(SortColumn column) {
//		availableSortColumns.add(column);
//	}
//
//	public void removeAvailableSortColumn(SortColumn column) {
//		for (int j = 0; j < availableSortColumns.size(); j++) {
//			if (availableSortColumns.get(j).getColumnName().equalsIgnoreCase(column.getColumnName())) {
//				availableSortColumns.remove(j);
//				break;
//			}
//		}
//	}
//
//	public void clearAvailableSortColumns() {
//		availableSortColumns.clear();
//	}
//
//	public List<SortColumn> getAvailableSortColumns() {
//		return availableSortColumns;
//	}
//
//	public void addSortColumn(SortColumn column) {
//		removeSortColumn(column);
//		sortColumns.add(column);
//	}
//
//	public void removeSortColumn(SortColumn column) {
//		for (int j = 0; j < sortColumns.size(); j++) {
//			if (sortColumns.get(j).getColumnKey().equalsIgnoreCase(column.getColumnKey())) {
//				sortColumns.remove(j);
//				break;
//			}
//		}
//	}
//
//	public void clearSortColumns() {
//		sortColumns.clear();
//	}
//
//	public List<SortColumn> getSortColumns() {
//		return sortColumns;
//	}
//
//	public String getSortColumn() {
//		return selectedSortColumn.getColumnKey();
//	}
//
//	public void setSortColumn(String sortColumn) {
//		clearSortColumns();
//		selectedSortColumn = new SortColumn("", sortColumn, SortColumnDirectionEnum.ASC);
//		addSortColumn(selectedSortColumn);
//	}
//
//	public String getSortDirection() {
//		return selectedSortColumn.getSortDirection().getSortColumnDirectionValue();
//	}
//
//	public void setSortDirection(String sortDirection) {
//		selectedSortColumn.setSortDirection(SortColumnDirectionEnum.valueOf(sortDirection));
//		addSortColumn(selectedSortColumn);
//	}
//
//	public SortColumn getSelectedSortColumn() {
//		return selectedSortColumn;
//	}
//
//	public void setSelectedSortColumn(SortColumn column) {
//		if (column != null) {
//			addSortColumn(column);
//			selectedSortColumn = column;
//		}
//	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = Utility.decorateString(criteria).toUpperCase();
	}

	public BaseForm() {
		baseReset();
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	private void baseReset() {
		formModifier = FormModifierEnum.NONE;
//		formAction = FormActionEnum.NONE;
		action = formModifier.getFormModifierValue();
	}

	public void reset() {
		baseReset();
//		paging = new PageGenerator();
//		pageRequested = "1";
	}

//	public String getDocAction() {
//		return this.docAction;
//	}

	public FormModifierEnum getFormModifier() {
		return formModifier;
	}

	public FormModifierEnum getFormModifierEnum() {
		return formModifier;
	}

	public void setFormMod(FormModifierEnum formModifierEnum) {
		setFormModifierEnum(formModifierEnum);
	}

	public void setFormModifier(FormModifierEnum formModifierEnum) {
		setFormModifierEnum(formModifierEnum);
	}

	public void setFormModifierEnum(FormModifierEnum formModifierEnum) {
		this.formModifier = formModifierEnum;
		this.action = formModifier.toString();
	}

//	public String getFormAction() {
//		return formAction.toString();
//	}
//
//	public FormActionEnum getFormActionEnum() {
//		return formAction;
//	}
//
//	public void setFormAction(String formAction) {
//		this.formAction = FormActionEnum.valueOf(formAction);
//	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		formModifier = FormModifierEnum.valueOf(action);
		this.action = action;
	}

	public String getTranslatedAction() {
		return this.formCode + "." + this.formModifier.getFormModifierValue();
	}

//	public String getTranslatedAction() {
//		return this.formCode + "." + this.getFormAction() + "."
//				+ this.getFormModifier();
//	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Object getAssertionKey() {
		return assertionKey;
	}

	public void setAssertionKey(Object assertionKey) {
		this.assertionKey = assertionKey;
	}

	public String getAttrKey() {
		return Constants.BASE_FORM_ATTR_KEY;
	}

	// ADDITIONAL BELOW
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(Integer accessCode) {
		this.accessCode = accessCode;
	}

	public Boolean getIsLookup() {
		return isLookup;
	}

	public void setIsLookup(Boolean isLookup) {
		this.isLookup = isLookup;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getPageSize() {
		return Constants.PAGE_SIZE;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public void addMessage(MessageTypeEnum messageTypeEnum, String msg) {
		this.messages.add(new Message(messageTypeEnum, msg));
	}

	public void addMessage(Exception e) throws Exception {
		String msg = null;
		MessageTypeEnum type = null;
		if (e instanceof JDBCException) {
			JDBCException ee = (JDBCException) e;
			if (ee.getErrorCode() == 20000) {
				msg = ExceptionTokenizer.getMessageSegment(e.getMessage(), 1);
				type = MessageTypeEnum.DANGER;
			} else if (ee.getErrorCode() == 20001) {
				msg = ExceptionTokenizer.getMessageSegment(e.getMessage(), 1);
				type = MessageTypeEnum.WARNING;
			} else if (ee.getErrorCode() == 20002) {
				msg = ExceptionTokenizer.getMessageSegment(e.getMessage(), 1);
				type = MessageTypeEnum.INFO;
			} else
				throw e;
		} else {
			msg = e.getMessage();
			type = MessageTypeEnum.WARNING;
		}

		messages.add(new Message(type, msg));

	}

	public void addMessage(List<FieldError> fieldErrors) {
		for (FieldError err : fieldErrors) {
			messages.add(new Message(MessageTypeEnum.WARNING, err.getField() + " : " + err.getDefaultMessage()));
		}
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Boolean getIsSubmitted() {
		return isSubmitted;
	}

	public void setIsSubmitted(Boolean isSubmitted) {
		this.isSubmitted = isSubmitted;
	}

}
