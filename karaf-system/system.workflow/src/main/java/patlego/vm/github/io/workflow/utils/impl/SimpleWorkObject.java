package patlego.vm.github.io.workflow.utils.impl;

import java.util.Map;

import patlego.vm.github.io.workflow.utils.WorkObject;

public class SimpleWorkObject implements WorkObject {

    private Map<String, Object> parameters;

    public SimpleWorkObject(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    
}