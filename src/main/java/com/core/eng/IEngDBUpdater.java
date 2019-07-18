package com.core.eng;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public interface IEngDBUpdater {

    /**
     * @brief to be called when data needs to be persisted
     * @param items contains a list of DB items
     * @throws IOException when it fails
     */
    void updateDB(final ArrayList<?> items) throws IOException;

    /**
     * @brief to fill the database
     * @param <T> is the type of items to be used
     * @return a collection of items
     * @throws IOException when it fails
     */
    <T> ArrayList<?> fromItems2DB() throws IOException;

    /**
     * @brief to retrive database content as a collection of items
     * @param <T> is the type of items to be used
     * @return a collection of items
     * @throws IOException when it fails
     */
    <T> ArrayList<?> fromDB2Items() throws IOException;

}
