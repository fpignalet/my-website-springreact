package com.core.eng;

import org.springframework.ui.Model;

import java.io.IOException;

public interface IEngModelUpdater {

    void updateModel(final Model model) throws IOException;

}
