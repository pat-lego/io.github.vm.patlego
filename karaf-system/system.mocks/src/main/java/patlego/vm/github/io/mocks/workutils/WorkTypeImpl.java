package patlego.vm.github.io.mocks.workutils;

import patlego.vm.github.io.workflow.utils.WorkType;
import patlego.vm.github.io.workflow.enums.ParamType;

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