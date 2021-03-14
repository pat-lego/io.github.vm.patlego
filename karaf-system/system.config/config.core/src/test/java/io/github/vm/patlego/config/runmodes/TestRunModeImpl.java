package io.github.vm.patlego.config.runmodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestRunModeImpl {

    @Test
    public void testRunModeService() {
        RunModeBean bean = new RunModeBean();
        bean.setRunmode("DEV");


        RunModeImpl runMode = new RunModeImpl();
        runMode.setRunModeBean(bean);

        assertEquals(RunModes.DEV, runMode.getRunMode());
    }
    
}
