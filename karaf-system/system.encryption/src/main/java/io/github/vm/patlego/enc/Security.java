package io.github.vm.patlego.enc;

import javax.annotation.Nonnull;

public interface Security {

    /**
     * Decrypts a token using the requested encryption service within the system
     * @param token Encrypted token, the token entered within the system must be encrypted using this or else decryption risks failing
     * @return Plain text token 
     */
    public @Nonnull String decrypt(@Nonnull String token);

    /**
     * Encrypts a token using paramters placed within the implementation
     * @param token - Plain text token to be encrypted
     * @return Encrpyted token 
     */
    public @Nonnull String encrypt(@Nonnull String token);
    
}
