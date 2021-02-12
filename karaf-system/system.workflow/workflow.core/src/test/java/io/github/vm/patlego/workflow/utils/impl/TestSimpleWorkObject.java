package io.github.vm.patlego.workflow.utils.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.github.vm.patlego.workflow.utils.WorkObject;

public class TestSimpleWorkObject {

    @Test
    public void testSimpleWorkObject() {
        WorkObject wo = new SimpleWorkObject();
        assertEquals(0, wo.getParameters().size());
    }

    @Test
    public void testSimpleWorkObject_withConstructor() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("TEST", new String("Sample Values"));
        WorkObject wo = new SimpleWorkObject(map);
        assertEquals(1, wo.getParameters().size());
    }
    
}