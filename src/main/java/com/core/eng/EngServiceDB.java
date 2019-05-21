package com.core.eng;

import com.core.data.DBHistContener;
import com.core.data.DBHistContenerDAO;
import com.core.data.DBItemTest;
import com.core.data.DBItemTestDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
@SessionAttributes("itemtest")
public class EngServiceDB implements IEngModelUpdater {

    /*************************************************************************
     INTERFACE ENFORCING
     *************************************************************************/
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
    private DBItemTestDAO data1Repo;

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
        data1Repo.save(item);
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
        final List<DBItemTest> items = data1Repo.findById(id);
        final DBItemTest item = items.get(0);
        item.setName(name);
        data1Repo.save(item);
        log.info("OK");
        return "NEW ITEM TEST UPDATED";
    }

    /**
     *
     */
    public void cleanAllItemsTest() {
        data1Repo.deleteAll();
    }

    /**
     * @return
     */
    public List<DBItemTest> findItemTestById(final int id){
        final List<DBItemTest> items = (List<DBItemTest>) data1Repo.findById(id);

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findItemTestByName(final String name){
        final List<DBItemTest> items = (List<DBItemTest>) data1Repo.findByName(name);

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public List<DBItemTest> findAllItemsTest() {
        final List<DBItemTest> items = (List<DBItemTest>) data1Repo.findAll();

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findAllItemTestsOrderedByIdDescending(){
        final List<DBItemTest> items = (List<DBItemTest>) data1Repo.findAllOrderedByIdDescending();

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBItemTest> findAllItemTestOrderedByNameDescending(){
        final List<DBItemTest> items = (List<DBItemTest>) data1Repo.findAllOrderedByNameDescending();

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /*************************************************************************
     DATABASE ACCESS ITEM TYPE1
     *************************************************************************/
    @Autowired
    private DBHistContenerDAO data2Repo;

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
        data2Repo.save(item);
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
        final List<DBHistContener> items = data2Repo.findById(id);
        final DBHistContener item = items.get(0);
//        item.setEntryname(theme);
        data2Repo.save(item);
        log.info("OK");
        return "NEW ITEM HIST UPDATED";
    }

    /**
     *
     */
    public void cleanAllItemsHist() {
        data2Repo.deleteAll();
    }

    /**
     * @return
     */
    public List<DBHistContener> findItemHistById(final int id){
        final List<DBHistContener> items = (List<DBHistContener>) data2Repo.findById(id);

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBHistContener> findItemHistByName(final String name){
        final List<DBHistContener> items = (List<DBHistContener>) data2Repo.findByName(name);

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public List<DBHistContener> findAllItemsHist() {
        final List<DBHistContener> items = (List<DBHistContener>) data2Repo.findAll();

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBHistContener> findAllItemsHistOrderedByIdDescending(){
        final List<DBHistContener> items = (List<DBHistContener>) data2Repo.findAllOrderedByIdDescending();

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /**
     * @return
     */
    public List<DBHistContener> findAllItemsHistOrderedByNameDescending(){
        final List<DBHistContener> items = (List<DBHistContener>) data2Repo.findAllOrderedByNameDescending();

        items.forEach((it)->{ log.info(items.toString()); });
        log.info("OK");
        return items;
    }

    /*************************************************************************
     INIT PART
     *************************************************************************/
    /**
     * @param data1Repo
     */
    public EngServiceDB(final DBItemTestDAO data1Repo, final DBHistContenerDAO data2Repo) {
        this.data1Repo = data1Repo;
        this.data2Repo = data2Repo;
        log.info("OK");
    }

}
