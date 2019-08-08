package com.core.eng.impl;

import com.core.data.IDBItemTestDAO;
import com.core.data.impl.sql.DBItemTest;
import com.core.eng.EEngModelItems;
import com.core.eng.IEngDBUpdater;
import com.core.eng.IEngModelUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Database operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
@SessionAttributes("itemTest")
public class EngServiceDBTest extends AEngJSONHandler implements IEngModelUpdater, IEngDBUpdater {

    /************************************************************************
     DATA ACCESS PART
     INTERFACE ENFORCING
     * @return
     */

    /**
     * @return
     */
    @ModelAttribute(name = "model_itemstest")
    public List<DBItemTest> allItems() {
        return findAllItemsTest();
    }

    @Override
    public void updateModel(final Model model) throws IOException {
        DBItemTest[] conteners = findAllItemTestsOrderedByIdAscending().toArray(new DBItemTest[0]);
        if(0 == conteners.length){
            conteners = fromItems2DB().toArray(new DBItemTest[0]);
        }

        model.addAttribute(EEngModelItems.DBTEST.getName(), conteners);
    }

    @Override
    public <T> ArrayList<?> updateDB(final ArrayList<?> items) {
        final ArrayList<DBItemTest> items_ = (ArrayList<DBItemTest>) items;
        final ArrayList<DBItemTest> itemsOut = new ArrayList<>();
        items_.forEach((conteneritem) -> {
            try {
                itemsOut.add(conteneritem);
                dataTestRepo.save(conteneritem);
            }
            catch(final Exception ex){
                log.error(ex.toString());
                log.debug(conteneritem.toString());
            }
        });

        return itemsOut;
    }

    @Override
    public <T> ArrayList<?> fromItems2DB() throws IOException {
        ///1. FROM FILE:
        final ArrayList<DBItemTest> items = (ArrayList<DBItemTest>) loadFile();
        ///2. TO DB:
        final ArrayList<DBItemTest> itemsOut = (ArrayList<DBItemTest>) updateDB(items);

        dataTestRepo.flush();
        return itemsOut;
    }

    @Override
    public <T> ArrayList<?> fromDB2Items() throws IOException {
        ///1. FROM DB:
        final ArrayList<DBItemTest> itemsDB = (ArrayList<DBItemTest>) findAllItemsTest();
        ///2. TO FILE:
        updateFile(itemsDB);
        return itemsDB;
    }

    /************************************************************************
     FILE ACCESS PART
     */

    @Override
    public <T> ArrayList<?> loadFile() throws IOException {
        return null;
    }

    @Override
    public void updateFile(ArrayList<?> itemsDB) throws IOException {
    }

    /************************************************************************
     MODIF PART
     */
    /**
     * @param name
     * @return
     */
    @Transactional
    public DBItemTest addOneItemTest(final String name) {
        final DBItemTest item = new DBItemTest();
        item.setName(name);
        dataTestRepo.save(item);
        log.debug(String.format("DB item %s created", item.toString()));
        return item;
    }

    /**
     * @param name
     * @return
     */
    @Transactional
    public DBItemTest updateOneItemTest(final String name) {
        final List<DBItemTest> items = dataTestRepo.findByName(name);
        final DBItemTest item = items.get(0);
        item.setName(name);
        dataTestRepo.save(item);
        log.debug(String.format("DB item %s updated", item.toString()));
        return item;
    }

    /**
     *
     */
    public void deleteAllItemsTest() {
        dataTestRepo.deleteAll();
    }

    /************************************************************************
     LOOK UP PART
     */
    /**
     * @return
     */
    public List<DBItemTest> findItemTestById(final int id){
        final List<DBItemTest> items = dataTestRepo.findById(id);
        items.forEach((it)-> log.debug(items.toString()));
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findItemTestByName(final String name){
        final List<DBItemTest> items = dataTestRepo.findByName(name);
        items.forEach((it)-> log.debug(items.toString()));
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public List<DBItemTest> findAllItemsTest() {
        final List<DBItemTest> items = dataTestRepo.findAll();
        items.forEach((it)-> log.debug(items.toString()));
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findAllItemTestsOrderedByIdAscending(){
        final List<DBItemTest> items = dataTestRepo.findAllOrderedByIdAscending();
        items.forEach((it)-> log.debug(items.toString()));
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findAllItemTestOrderedByNameAscending(){
        final List<DBItemTest> items = dataTestRepo.findAllOrderedByNameAscending();
        items.forEach((it)-> log.debug(items.toString()));
        return items;
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param dataTestRepo autowired IDBItemTestDAO
     */
    public EngServiceDBTest(final IDBItemTestDAO dataTestRepo) {
        this.dataTestRepo = dataTestRepo;
    }

    @Autowired
    private IDBItemTestDAO dataTestRepo;

}
