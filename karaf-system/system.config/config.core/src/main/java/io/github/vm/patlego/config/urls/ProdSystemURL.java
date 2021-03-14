package io.github.vm.patlego.config.urls;

public class ProdSystemURL implements SystemURL {

    @Override
    public String getHostName() {
        return "www.pat-lego.com";
    }

    @Override
    public Integer getPort() {
       return 80;
    }
    
}
