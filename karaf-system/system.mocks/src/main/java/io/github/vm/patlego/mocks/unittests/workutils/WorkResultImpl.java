package io.github.vm.patlego.mocks.unittests.workutils;

import java.util.Map;

import io.github.vm.patlego.workflow.utils.WorkResult;

public class WorkResultImpl implements WorkResult {

    private Boolean hasSucceedeed = true;
    private Map<String, Object> parameters;

    public WorkResultImpl(Boolean success, Map<String, Object> parameters) {
        this.hasSucceedeed = success;
        this.parameters = parameters;
    }

    @Override
    public Boolean hasSucceeded() {
       return this.hasSucceedeed;
    }

    @Override
    public Exception getException() {
        return null;
    }

    @Override
    public Map<String, Object> getParameters() {
       return this.parameters;
    }
    
}