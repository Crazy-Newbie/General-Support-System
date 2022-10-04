package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

public class ToolForm extends BaseForm {

    private static final String FORM_CODE = "TOOL_FORM";

    private String userId;
    private String userName;
    private String surveyId;
    private String surveyTitle; // helper input form
    private String anonymStatus;
    private boolean isAll;

    public ToolForm() {
        reset();
    }

    public void reset() {
        setFormCode(FORM_CODE);
        userId = "";
        userName = "";
        surveyId = "";
        surveyTitle = "";
        anonymStatus = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean isAll) {
        this.isAll = isAll;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getAnonymStatus() {
        return anonymStatus;
    }

    public void setAnonymStatus(String anonymStatus) {
        this.anonymStatus = anonymStatus;
    }

}
