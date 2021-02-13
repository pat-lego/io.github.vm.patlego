package io.github.vm.patlego.mail.template;

import javax.annotation.Nonnull;

public interface Templater {

    /**
     * Returns a templated string
     * @return String
     */
    public @Nonnull String templateString(@Nonnull String content);
    
}
