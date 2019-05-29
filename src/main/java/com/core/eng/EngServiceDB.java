package com.core.eng;

import com.core.data.*;
import com.core.data.impl.DBHistContener;
import com.core.data.impl.DBItemTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Database operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
@SessionAttributes("itemtest")
public class EngServiceDB implements IEngModelUpdater {

    /************************************************************************
     INTERFACE ENFORCING
     */
    /**
     * in resources/templates/fragments:
     */
    private final static String[] modelItems = {
        "collection" //for contentXXX.html
    };

    /**
     * @param model
     */
    @Override
    public void updateModel(final Model model) {
        model.addAttribute(modelItems[0], findAllItemsTest().toArray(new DBItemTest[0]));
    }

    /*************************************************************************
     DATABASE ACCESS ITEM TEST
     *************************************************************************/
    @Autowired
    private DBItemTestDAO dataTestRepo;

    @ModelAttribute(name = "item")
    public DBItemTest itemtest() {
        return new DBItemTest();
    }

    /**
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String addOneItemTest(final int id, final String name) {
        final DBItemTest item = new DBItemTest();
        item.setId(id);
        item.setName(name);
        dataTestRepo.save(item);
        log.info("OK");
        return "NEW ITEM TEST SAVED";
    }

    /**
     * @param id
     * @param name
     * @return
     */
    @Transactional
    public String updateOneItemTest(final int id, final String name) {
        final List<DBItemTest> items = dataTestRepo.findById(id);
        final DBItemTest item = items.get(0);
        item.setName(name);
        dataTestRepo.save(item);
        log.info("OK");
        return "NEW ITEM TEST UPDATED";
    }

    /**
     *
     */
    public void cleanAllItemsTest() {
        dataTestRepo.deleteAll();
    }

    /**
     * @return
     */
    public List<DBItemTest> findItemTestById(final int id){
        final List<DBItemTest> items = dataTestRepo.findById(id);

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findItemTestByName(final String name){
        final List<DBItemTest> items = dataTestRepo.findByName(name);

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public List<DBItemTest> findAllItemsTest() {
        final List<DBItemTest> items = dataTestRepo.findAll();

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findAllItemTestsOrderedByIdDescending(){
        final List<DBItemTest> items = dataTestRepo.findAllOrderedByIdDescending();

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findAllItemTestOrderedByNameDescending(){
        final List<DBItemTest> items = dataTestRepo.findAllOrderedByNameDescending();

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /*************************************************************************
     DATABASE ACCESS ITEM TYPE1
     *************************************************************************/
    @Autowired
    private DBHistContenerDAO dataHistContenerRepo;

    @Autowired
    private DBHistItemDAO dataHistItemRepo;

    @Autowired
    private DBHistContentDAO dataHistContentRepo;

    @Autowired
    private DBHistSubDAO dataHistSubRepo;

    @ModelAttribute(name = "item")
    public DBHistContener itemhist() {
        return new DBHistContener();
    }

    /**
     * @param id
     * @param theme
     * @return
     */
    @Transactional
    public String addOneItemHist(final int id, final String[] theme) {
        final DBHistContener item = new DBHistContener();
        item.setId(id);
//        item.setEntryname(theme);
        dataHistContenerRepo.save(item);
        log.info("OK");
        return "NEW ITEM HIST SAVED";
    }

    /**
     * @param id
     * @param theme
     * @return
     */
    @Transactional
    public String updateOneItemHist(final int id, final String[] theme) {
        final List<DBHistContener> items = dataHistContenerRepo.findById(id);
        final DBHistContener item = items.get(0);
//        item.setEntryname(theme);
        dataHistContenerRepo.save(item);
        log.info("OK");
        return "NEW ITEM HIST UPDATED";
    }

    /**
     *
     */
    public void cleanAllItemsHist() {
        dataHistContenerRepo.deleteAll();
        dataHistContentRepo.deleteAll();
        dataHistItemRepo.deleteAll();
        dataHistSubRepo.deleteAll();
    }

    /**
     * @return
     */
    public List<DBHistContener> findItemHistById(final int id){
        final List<DBHistContener> items = dataHistContenerRepo.findById(id);

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBHistContener> findItemHistByName(final String name){
        final List<DBHistContener> items = dataHistContenerRepo.findByName(name);

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public List<DBHistContener> findAllItemsHist() {
        final List<DBHistContener> items = dataHistContenerRepo.findAll();

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBHistContener> findAllItemsHistOrderedByIdDescending(){
        final List<DBHistContener> items = dataHistContenerRepo.findAllOrderedByIdDescending();

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBHistContener> findAllItemsHistOrderedByNameDescending(){
        final List<DBHistContener> items = dataHistContenerRepo.findAllOrderedByNameDescending();

        items.forEach((it)-> log.info(items.toString()));
        log.info("OK");
        return items;
    }

    /**
     * @param itemsJSON
     */
    public void populateDB(final ArrayList<DBHistContener> itemsJSON) {
        itemsJSON.forEach((conteneritem) -> {
            try {
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
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @param dataTestRepo
     * @param dataHistContenerDAO
     * @param dataHistItemDAO
     * @param dataHistContentRepo
     * @param dataHistSubRepo
     */
    public EngServiceDB(
        final DBItemTestDAO dataTestRepo,
        final DBHistContenerDAO dataHistContenerDAO,
        final DBHistItemDAO dataHistItemDAO,
        final DBHistContentDAO dataHistContentRepo,
        final DBHistSubDAO dataHistSubRepo) {

        this.dataTestRepo = dataTestRepo;
        this.dataHistContenerRepo = dataHistContenerDAO;
        this.dataHistItemRepo = dataHistItemDAO;
        this.dataHistContentRepo = dataHistContentRepo;
        this.dataHistSubRepo = dataHistSubRepo;
        log.info("OK");

    }

}
