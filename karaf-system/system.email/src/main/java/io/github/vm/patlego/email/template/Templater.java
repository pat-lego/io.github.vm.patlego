package io.github.vm.patlego.email.template;

public interface Templater {

    /**
     * Returns a templated string
     * @return String
     */
    public String templateString(String content);
    
}
