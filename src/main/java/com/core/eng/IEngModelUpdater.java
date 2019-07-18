package com.core.eng;

import org.springframework.ui.Model;

import java.io.IOException;

/**
 *
 */
public interface IEngModelUpdater {

    /**
     * @brief to be called when GUI needs to display data
     * @param model
     * @throws IOException
     */
    void updateModel(final Model model) throws IOException;

}
