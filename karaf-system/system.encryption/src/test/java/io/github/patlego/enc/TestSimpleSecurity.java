package io.github.patlego.enc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.vm.patlego.enc.impl.SimpleSecurity;

public class TestSimpleSecurity {

    @Test
    public void testEnc() {
        SimpleSecurity security = new SimpleSecurity();
        security.activate();
        String result = security.encrypt("test");
        assertEquals("test", security.decrypt(result));
    }
    
}
