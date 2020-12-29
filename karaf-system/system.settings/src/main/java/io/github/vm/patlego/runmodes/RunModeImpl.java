package io.github.vm.patlego.runmodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunModeImpl implements RunMode {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RunModeBean runModeBean;

    public void setRunModeBean(RunModeBean runModeBean) {
        this.runModeBean = runModeBean;
    }

    @Override
    public RunModes getRunMode() {
        String runMode = this.runModeBean.getRunmode();
        return RunModes.valueOf(runMode.toUpperCase());
    }

    public void init() {
        logger.info(String.format("Runmode is set too %s", runModeBean.getRunmode()));
    }
    
}
