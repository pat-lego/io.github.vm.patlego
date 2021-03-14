package io.github.vm.patlego.config.urls;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestDevSystemUrl {

    @Test
    public void testDevSystemUrl() {
        SystemURL urls = new DevSystemURL();
        assertEquals("localhost", urls.getHostName());
        assertEquals(8181, urls.getPort());
    }
    
}
