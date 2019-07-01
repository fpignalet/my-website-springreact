package com.core.eng;

import com.core.data.*;
import com.core.data.impl.*;
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
import java.util.Arrays;
import java.util.HashMap;
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
     * @param items
     */
    public void update(final ArrayList<DBHistContener> items) {
        items.forEach((conteneritem) -> {
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

    /**
     *
     * @param conteners
     * @param params
     * @return
     */
    public List<DBHistContener> filter(final DBConteners conteners, final String[] params) {
        if(true == params[0].equals("cliside_ENTRYphpmail")){
        }
        else if(true == params[0].startsWith("cliside_BLOGphptest")){
        }
        else {
            final HashMap<String, ArrayList<DBHistContener>> data = new HashMap<String, ArrayList<DBHistContener>>();
            for(final String entry: new String[]{
                "BLOGNEWSENTRY", "BLOGTECHENTRY",
                "JOBENTRY",
                "EXPENTRY",
                "DESCENTRY", "INFOENTRY",
                "SKILLSENTRY", "LANGENTRY",
                "GRADUATIONENTRY", "HOBBYENTRY"
            }) {
                data.put(entry, new ArrayList<>());
            }

            conteners.forEach((v) -> {
                data.get(v.getContenertype()).add(v);
            });

            final List<DBHistContener> itemsDB = new ArrayList<>();

            if(true == params[0].equals("cliside_BLOGphpgetdata")){
                final DBHistContener item = mapBLOG(params, data);
                itemsDB.add(item);
            }
            else if(true == params[0].equals("cliside_CVphpgetdata")){
                final DBHistContener item = mapCV(params, data);
                itemsDB.add(item);
            }

            return itemsDB;
        }

        return null;
    }

    /**
     * @param params
     * @param data
     * @return
     */
    protected DBHistContener mapBLOG(final String[] params, final HashMap<String, ArrayList<DBHistContener>> data) {
        String sel = "";
        String key = "";
        int index = 0;

            /*
                data_BNdesc21
                    "blog_entry21PHOTO" =>"",
                    "blog_entry21TITLE" =>"",
                    "blog_entry21DATE" =>["",""]
                $data_BNcontent21 = [""=>[["p/ul/a",[""]]]];
            */
        sel = "data_BN";
        if(true == params[1].startsWith(sel)){
            key = "BLOGNEWSENTRY";
            final List list = Arrays.asList(new Integer[] { 20, 17, 14, 12, 10, 9, 6, 4, 3, 2, 1 });
            index = list.indexOf(Integer.parseInt(params[1].substring((sel+"desc").length())));
        }
        else {
            sel = "data_BT";
            if(true == params[1].startsWith(sel)){
                key = "BLOGTECHENTRY";
                final List list = Arrays.asList(new Integer[] { 19, 18, 16, 15, 13, 11, 8, 7, 5 });
                index = list.indexOf(Integer.parseInt(params[1].substring((sel+"desc").length())));
            }
        }

        final DBHistContener item = data.get(key).get(index);
        log.info("transformed request to key={} index={}", key, index);
//        log.info("transformed request to key={} index={} with={}", key, index, item.toString());
        return item;
    }

    /**
     * @param params
     * @param data
     * @return
     */
    protected DBHistContener mapCV(final String[] params, final HashMap<String, ArrayList<DBHistContener>> data) {
        String sel = "";
        String key = "";
        int index = 0;

            /*
                data_CVtitle
                    "moi_photo" =>""
                    "moi_name" =>""
                data_CVinfo
                    "info_raisonsociale" =>[""]
                    "info_adresse" =>[""]
                    "info_email" =>[""]
                    "info_phonenum" =>[""]
                    "info_geburstag" =>[""]
                data_CVexperience
                    "exp_title" => ""
                    "exp_content" =>[""]
                data_CVlanghead
                    "lang_title" =>""
                data_CVlangentries
                    [
                        "lang_f1desc" =>[""]
                        "lang_f1level" =>[""]
                        "lang_f1text" =>[""]
                    ],
                data_CVskillshead
                    "mskills_title" =>""
                data_CVskillsentries
                    [
                        "mskills_f1desc" =>[""]
                        "mskills_f1level" =>[""]
                        "mskills_f1text" =>[""]
                    ],
                data_CVboulot0 1 2 3 4 5 6 7
                    "boulotentry0date" =>[""]
                    "boulotentry0boite" =>["",[""]]
                    "boulotentry0desc" =>[]
                data_CVboulot01 11 21..26 31..32 41 51..53 61..62 71
                    "boulotentry0date" =>[""]
                    "boulotentry0boite" =>["",[""]]
                    "boulotentry0desc" =>[]
                data_CVhobby1
                    "loisirs_f1date" => [""],
                    "loisirs_f1boite" => null,
                    "loisirs_f1desc" => null
                data_CVhobby11 12 13
                    "loisirs_f11item" =>
                    "loisirs_f11title" =>
                    "loisirs_f11content" =>
                        [["","",[["",""]]]]
                data_CVbildung1
                    "edu_f1date" => [""],
                    "edu_f1boite" => null,
                    "edu_f1desc" => null
                data_CVbildung11 12 13
                    "edu_f11item" =>
                    "edu_f11title" =>
                    "edu_f11content" =>
                        [["","",[["",""]]]]
            */
        sel = "data_CVtitle";
        if(true == params[1].startsWith(sel)) {
            key = "DESCENTRY";
        }
        else {
            sel = "data_CVinfo";
            if(true == params[1].startsWith(sel)) {
                key = "INFOENTRY";
            }
            else {
                sel = "data_CVexperience";
                if(true == params[1].startsWith(sel)) {
                    key = "DESCENTRY"; //conteneritems
                }
                else{
                    sel = "data_CVlangentries";
                    if(true == params[1].startsWith(sel)) {
                        key = "LANGENTRY";
                        index = Integer.parseInt(params[1].substring((sel).length()));
                    }
                    else{
                        sel = "data_CVskillsentries";
                        if(true == params[1].startsWith(sel)) {
                            key = "SKILLSENTRY";
                            index = Integer.parseInt(params[1].substring((sel).length()));
                        }
                        else{
                            sel = "data_CVboulot";
                            if(true == params[1].startsWith(sel)) {
                                key = "JOBENTRY";
                                index = Integer.parseInt(params[1].substring((sel).length()));
                            }
                        }
                    }
                }
            }
        }

        final DBHistContener item = data.get(key).get(index);
        log.info("transformed request to key={} index={}", key, index);
//        log.info("transformed request to key={} index={} with={}", key, index, item.toString());
        return item;
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
