package patlego.vm.github.io.workflow.impl;

import java.util.HashMap;
import java.util.Map;

import patlego.vm.github.io.workflow.utils.WorkObject;

public class SimpleWorkObject implements WorkObject {

    private Map<String, Object> parameters;
    
    public SimpleWorkObject() {
        this.parameters = new HashMap<String, Object>();
    }

    public SimpleWorkObject(Map<String, Object> parameters) {
        if (parameters == null) {
            this.parameters =  new HashMap<String, Object>();
        }
        this.parameters = parameters;
    }

    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }
    
}