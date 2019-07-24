package com.core.eng.impl;

import com.core.data.IDBHistContenerDAO;
import com.core.data.impl.DBCVConteners;
import com.core.data.impl.DBHistContener;
import com.core.eng.EEngJSONFiles;
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
 * CV Database operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
@SessionAttributes("itemCV")
public class EngServiceDBHistory extends AEngJSONHandler implements IEngModelUpdater, IEngDBUpdater {

    /************************************************************************
     INTERFACE ENFORCING
     DATA ACCESS PART
     */

    /**
     * @return
     */
    @ModelAttribute(name = "model_CVHist")
    public DBHistContener itemhist() {
        return new DBHistContener();
    }

    @Override
    public void updateModel(final Model model) throws IOException {
        DBCVConteners conteners = findAllItemsHistOrderedByIdAscending();
        if(0 == conteners.size()){
            conteners = (DBCVConteners) fromItems2DB();
        }

        model.addAttribute(EEngModelItems.DBHIST.getName(), conteners);
        model.addAttribute(EEngModelItems.JSONHIST.getName(), conteners);
    }

    @Override
    public <T> ArrayList<?> updateDB(final ArrayList<?> items) {
        final DBCVConteners itemsIn = (DBCVConteners) items;
        final DBCVConteners itemsOut = new DBCVConteners();
        itemsIn.forEach((conteneritem) -> {
            try {
                itemsOut.add(conteneritem);

                //DBHistContener -> DBHistItem
                conteneritem.getConteneritems().forEach((histitem) -> {
                    histitem.setParent(conteneritem);

                    //DBHistItem -> DBHistContent
                    histitem.getContentitems().forEach((contentitem) -> {
                        contentitem.setParent(histitem);

                        //DBHistContent -> DBHisSub
                        contentitem.getContentlist().forEach((subitem) -> {
                            subitem.setParent(contentitem);

                            //DBHisSub -> DBHistText
                            subitem.getListtext().forEach((textitem) -> {
                                textitem.setParent(subitem);
                            });
                        });
                    });
                });

                dataHistContenerRepo.save(conteneritem);
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
        final DBCVConteners conteners = (DBCVConteners) loadFile();
        ///2. TO DB:
        final DBCVConteners itemsOut = (DBCVConteners) updateDB(conteners);

        dataHistContenerRepo.flush();
        return itemsOut;
    }

    @Override
    public <T> ArrayList<?> fromDB2Items() throws IOException {
        ///1. FROM DB:
        final DBCVConteners itemsDB = findAllItemsHist();
        ///2. TO FILE:
        updateFile(itemsDB);
        return itemsDB;
    }

    /************************************************************************
     INTERFACE ENFORCING
     FILE ACCESS PART
     */

    @Override
    public <T> ArrayList<?> loadFile() throws IOException {
        final String data = load(EEngJSONFiles.HISTIN.getName());
        return (DBCVConteners) parse(data, DBCVConteners.class, DBCVConteners.getSubItems());
    }

    @Override
    public void updateFile(final ArrayList<?> itemsDB) throws IOException {
        final String jsonString = fill(itemsDB);
        save(EEngJSONFiles.HISTOUT.getName(), jsonString);
    }

    /************************************************************************
     MODIF PART
     */
    /**
     * @param id
     * @param theme
     * @return
     */
    @Transactional
    public DBHistContener addOneItemHist(final int id, final String[] theme) {
        final DBHistContener item = new DBHistContener();
//        item.setId(id);
//        item.setEntryname(theme);

        dataHistContenerRepo.save(item);

        log.debug("DB Hist item %s created", item.toString());
        return item;
    }

    /**findById
     * @param id
     * @param theme
     * @return
     */
    @Transactional
    public DBHistContener updateOneItemHist(final int id, final String[] theme) {
        final DBCVConteners items = findItemHistById(id);

        final DBHistContener item = items.get(0);

        dataHistContenerRepo.save(item);

        log.debug("DB Hist item %s updated", item.toString());
        return item;
    }

    /**
     *
     */
    public void deleteAllItemsHist() {
        dataHistContenerRepo.deleteAll();
        log.debug("DB empty");
    }

    /************************************************************************
     LOOK UP PART
     */

    /**
     * @return
     */
    public DBCVConteners findItemHistById(final int id){
        final List<DBHistContener> items = dataHistContenerRepo.findById(id);
        items.forEach((it)-> log.debug(items.toString()));
        return new DBCVConteners(items);
    }

    /**
     * @return
     */
    public DBCVConteners findItemHistByName(final String name){
        final List<DBHistContener> items = dataHistContenerRepo.findByName(name);
        items.forEach((it)-> log.debug(items.toString()));
        return new DBCVConteners(items);
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public DBCVConteners findAllItemsHist() {
        final List<DBHistContener> items = dataHistContenerRepo.findAll();
        items.forEach((it)-> log.debug(items.toString()));
        return new DBCVConteners(items);
    }

    /**
     * @return
     */
    public DBCVConteners findAllItemsHistOrderedByIdAscending(){
        final List<DBHistContener> items = dataHistContenerRepo.findAllOrderedByIdAscending();
        items.forEach((it)-> log.debug(items.toString()));
        return new DBCVConteners(items);
    }

    /**
     * @return
     */
    public DBCVConteners findAllItemsHistOrderedByNameAscending(){
        final List<DBHistContener> items = dataHistContenerRepo.findAllOrderedByNameAscending();
        items.forEach((it)-> log.debug(items.toString()));
        return new DBCVConteners(items);
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param dataHistContenerDAO autowired IDBHistContenerDAO
     */
    public EngServiceDBHistory(final IDBHistContenerDAO dataHistContenerDAO) {
        this.dataHistContenerRepo = dataHistContenerDAO;
    }

    @Autowired
    private IDBHistContenerDAO dataHistContenerRepo;

}
