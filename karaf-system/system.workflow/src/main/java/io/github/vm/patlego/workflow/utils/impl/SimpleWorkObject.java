/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.utils.impl;

import java.util.HashMap;
import java.util.Map;

import io.github.vm.patlego.workflow.utils.WorkObject;

public class SimpleWorkObject implements WorkObject {

    private Map<String, Object> parameters;

    public SimpleWorkObject() {
        this.parameters = new HashMap<String,Object>();
    }

    public SimpleWorkObject(Map<String, Object> parameters) {
        if (parameters == null) {
            this.parameters = new HashMap<String,Object>();
        }
        this.parameters = parameters;
    }

    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    
}