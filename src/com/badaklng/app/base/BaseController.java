/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.badaklng.app.base;

import org.springframework.web.bind.WebDataBinder;

/**
 *
 * @author pt badak
 */
public abstract class BaseController {
    
    public abstract void initBinder(WebDataBinder binder);
}
