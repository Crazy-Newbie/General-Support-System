/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badaklng.app.model;

import com.badaklng.app.base.BaseForm;

/**
 *
 * @author NN6ZZN2212
 */
public class ParameterForm extends BaseForm {
    
    private static final String FORM_CODE = "PARAMETER_FORM";
    
    private String paramKey;
    private String desc;
    private String value;
    
    public ParameterForm(){
        reset();
    }
    
    public void reset(){
        setFormCode(FORM_CODE);
        paramKey = "";
        desc = "";
        value = "";
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
