package com.badaklng.app.model;

import java.util.HashMap;
import java.util.Map;

import com.badaklng.app.base.BaseForm;
import com.badaklng.app.constant.LogKeyAttributeEnum;
import com.badaklng.app.hibernate.AppUser;
import com.badaklng.app.utilities.Utility;

public class LogIntention {

    protected String type;
    protected Map<String, String> args = new HashMap<String, String>(0);
    protected AppUser user;

    public void registerArguments(LogKeyAttributeEnum key, String value) {
        args.put(key.toString(), value);
    }
    
    public void registerArguments(String key, String value){
    	args.put(key, value);
    }

    public LogIntention(AppUser user, Map<String, String> args) {
        setUser(user);
        setArguments(args);
    }

    public LogIntention(AppUser user) {
        setUser(user);
    }
    
    public LogIntention(AppUser user, BaseForm form) {
		setUser(user);
		setType(form.getTranslatedAction());
	}

    public AppUser getUser() {
        return user == null ? new AppUser() : user;
    }

    public void setUser(AppUser user) {
        if (user != null) {
            this.user = user;
        }
    }

    public void setArguments(Map<String, String> args) {
        if (args != null) {
            this.args = args;
        }
    }

    public Map<String, String> getArguments() {
        return args;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Utility.decorateString(type).toUpperCase();
    }

    public String translateArguments() {
        StringBuilder argsBuilder = new StringBuilder();
        for (String s : this.getArguments().keySet()) {
            argsBuilder.append("[" + s + "=" + this.getArguments().get(s) + "]");
        }
        return argsBuilder.toString();
    }

}
