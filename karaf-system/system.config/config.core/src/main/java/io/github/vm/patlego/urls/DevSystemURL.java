package io.github.vm.patlego.urls;

public class DevSystemURL implements SystemURL {

    @Override
    public String getHostName() {
        return "localhost";
    }

    @Override
    public Integer getPort() {
        return 8181;
    }
    
}
