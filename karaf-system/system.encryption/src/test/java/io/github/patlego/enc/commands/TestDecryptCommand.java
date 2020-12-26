package io.github.patlego.enc.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.github.vm.patlego.enc.commands.DecryptCommand;
import io.github.vm.patlego.enc.impl.SimpleSecurity;

public class TestDecryptCommand {

    @Test
    public void testDecrypt() throws Exception {
        DecryptCommand enc = new DecryptCommand();
        SimpleSecurity security = new SimpleSecurity();
        security.activate();
        enc.security = security;
        enc.input = "6URY78DsykJOWRNfpiW/fA==";
        assertNotNull(enc.execute());
        assertEquals("test", enc.execute());
    }
    
}
