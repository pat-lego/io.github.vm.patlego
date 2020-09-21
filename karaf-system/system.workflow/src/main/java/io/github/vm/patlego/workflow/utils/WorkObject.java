/*
 * Created on Mon Jul 20 2020
 *
 * @author Patrique Legault
 * @version 1.0
 * @since Mon Jul 20 2020
 *
 * Copyright (c) 2020 LegoTech
 */

package io.github.vm.patlego.workflow.utils;

import java.util.Map;

import javax.annotation.Nonnull;

public interface WorkObject {

    /**
     * Returns the parameters that are part of the map prior to the beginning of the WorkItem execution
     * @return Map
     */
    public @Nonnull Map<String, Object> getParameters();
}