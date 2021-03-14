package io.github.vm.patlego.config.urls;

import javax.annotation.Nonnull;

public interface SystemURL {
    /**
     * Retrieve the specific host name for the environment looking to be used
     * @return String - Representing host name
     */
    public @Nonnull String getHostName();

    /**
     * Retrieve the specific port for the environment looking to be used
     * @return Integer - Representing port
     */
    public @Nonnull Integer getPort();
}