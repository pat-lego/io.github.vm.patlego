package io.github.vm.patlego.enc;

public interface Security {

    public String decrypt(String token);

    public String encrypt(String token);
    
}
