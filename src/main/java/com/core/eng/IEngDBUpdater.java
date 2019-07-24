package com.core.eng;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public interface IEngDBUpdater {

    /**
     * @brief to be called when data needs to be persisted
     * @param <T> is the type of items to be used
     * @param items contains a list of DB items
     * @throws IOException when it fails
     */
    <T> ArrayList<?> updateDB(final ArrayList<?> items) throws IOException;

    /**
     * @param <T> is the type of items to be used
     * @return a collection of items
     * @throws IOException when it fails
     */
    <T> ArrayList<?> fromItems2DB() throws IOException;

    /**
     * @param <T> is the type of items to be used
     * @return a collection of items
     * @throws IOException when it fails
     */
    <T> ArrayList<?> fromDB2Items() throws IOException;

}
