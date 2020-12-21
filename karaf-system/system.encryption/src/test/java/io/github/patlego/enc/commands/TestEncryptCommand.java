package io.github.patlego.enc.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import io.github.vm.patlego.enc.commands.EncryptCommand;
import io.github.vm.patlego.enc.impl.SimpleSecurity;

public class TestEncryptCommand {

    @Test
    public void testEncrypt() throws Exception {
        EncryptCommand enc = new EncryptCommand();
        SimpleSecurity security = new SimpleSecurity();
        security.activate();
        enc.security = security;
        enc.input = "test";
        assertNotNull(enc.execute());
    }
    
}
