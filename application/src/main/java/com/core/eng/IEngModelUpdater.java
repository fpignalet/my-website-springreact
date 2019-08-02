package com.core.eng;

import org.springframework.ui.Model;

import java.io.IOException;

/**
 *
 */
public interface IEngModelUpdater {

    /**
     * @param model
     * @throws IOException
     */
    void updateModel(final Model model) throws IOException;

}
