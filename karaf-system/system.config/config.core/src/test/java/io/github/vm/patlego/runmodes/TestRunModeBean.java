package io.github.vm.patlego.runmodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestRunModeBean {

    @Test
    public void testBean() {
        RunModeBean bean = new RunModeBean();
        bean.setRunmode("DEV");
        assertEquals("DEV", bean.getRunmode());
    }
    
}
