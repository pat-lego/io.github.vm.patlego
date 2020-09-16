package io.github.vm.patlego.mocks.unittests.workutils;

import io.github.vm.patlego.workflow.utils.WorkType;
import io.github.vm.patlego.workflow.enums.ParamType;

public class WorkTypeImpl implements WorkType {
    private ParamType paramType;
    private String className;

    public WorkTypeImpl(ParamType paramType, String className) {
        this.className = className;
        this.paramType = paramType;
    }

    @Override
    public ParamType getType() {
        return this.paramType;
    }

    @Override
    public String getClassName() {
       if (this.className == null) {
           throw new IllegalArgumentException("Cannot have a null class name please provide one");
       }

       return this.className;
    }
    
}