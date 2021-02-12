package io.github.vm.patlego.urls;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestProdSystemUrl {

    @Test
    public void testDevSystemUrl() {
        SystemURL urls = new ProdSystemURL();
        assertEquals("www.pat-lego.com", urls.getHostName());
        assertEquals(80, urls.getPort());
    }
    
}
