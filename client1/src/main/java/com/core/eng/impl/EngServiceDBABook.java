package com.core.eng.impl;

import com.core.data.IDBContactDAO;
import com.core.data.IDBTokenDAO;
import com.core.data.impl.sql.DBAddressBook;
import com.core.data.impl.sql.DBContact;
import com.core.data.impl.sql.DBToken;
import com.core.eng.EEngJSONFiles;
import com.core.eng.EEngModelItems;
import com.core.eng.IEngDBUpdater;
import com.core.eng.IEngModelUpdater;
import com.core.utils.UtGeneralException;
import lombok.AccessLevel;
import lombok.Getter;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * AddressBook operations
 */
@Slf4j
@Service
@ComponentScan({"com.core.data"})
@SessionAttributes("itemABook")
public class EngServiceDBABook extends AEngJSONHandler implements IEngModelUpdater, IEngDBUpdater {

    /************************************************************************
     INTERFACE ENFORCING
     DATA ACCESS PART
     */

    /**
     * @return
     */
    @ModelAttribute(name = "model_addressbookall")
    public List<DBContact> allContacts() {
        return findAllContactOrderedByIdAscending();
    }

    @Override
    public void updateModel(final Model model) throws IOException {
        DBAddressBook conteners = findAllContactOrderedByIdAscending();
        if(0 == conteners.size()){
            conteners = (DBAddressBook) fromItems2DB();
        }

        model.addAttribute(EEngModelItems.DBABOOK.getName(), conteners);
        model.addAttribute(EEngModelItems.JSONABOOK.getName(), conteners);
    }

    @Override
    public <T> ArrayList<?> updateDB(final ArrayList<?> items) {
        final DBAddressBook itemsIn = (DBAddressBook) items;
        final DBAddressBook itemsOut = new DBAddressBook();
        itemsIn.forEach((conteneritem) -> {
            try {
                itemsOut.add(conteneritem);
                dataContactRepo.save(conteneritem);
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
        final DBAddressBook conteners = (DBAddressBook) loadFile();
        ///2. TO DB:
        final DBAddressBook itemsOut = (DBAddressBook) updateDB(conteners);

        dataContactRepo.flush();
        return itemsOut;
    }

    @Override
    public <T> ArrayList<?> fromDB2Items() throws IOException {
        ///1. FROM DB:
        final DBAddressBook itemsDB = findAllContactOrderedByIdAscending();
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
        final String data = load(EEngJSONFiles.ADBOOKIN.getName());
        return (DBAddressBook) parse(data, DBAddressBook.class, DBAddressBook.getSubItems());
    }

    @Override
    public void updateFile(final ArrayList<?> itemsDB) throws IOException {
        final String jsonString = fill(itemsDB);
        save(EEngJSONFiles.ADBOOKOUT.getName(), jsonString);
    }

    /************************************************************************
     MODIF PART
     */
    /**
     * @brief add a new contact
     * @param vorname contains vorname value to be saved
     * @param nachname contains nachname value to be saved
     * @param emailadresse contains emailadresse value to be saved
     * @return the created DBContact instance
     * @throws UtGeneralException when it fails
     */
    @Transactional
    public DBContact addOneContact(
        final String vorname,
        final String nachname,
        final String emailadresse,
        final boolean enabled
    ) throws UtGeneralException {

        final DBAddressBook contacts = findByVorNNachname(vorname, nachname);
        if(0 < contacts.size()) {
            throw new UtGeneralException("DB contact NOT created. Contact already exists", new Throwable());
        }

        final DBContact contact = new DBContact();
        contact.setVorname(vorname);
        contact.setNachname(nachname);
        contact.setEmailadresse(emailadresse);
        contact.setEnabled(enabled);

        dataContactRepo.save(contact);

        log.debug("DB contact %s created", contact.toString());
        return contact;
    }

    /**
     * @brief confirm a newly added contact
     * @param vorname contains new vorname value to be applied
     * @param nachname contains new nachname value to be applied
     * @return the related security token
     * @throws UtGeneralException
     */
    @Transactional
    public DBToken verifyOneContact(
        final String vorname,
        final String nachname) throws UtGeneralException {

        final DBAddressBook contacts = findByVorNNachname(vorname, nachname);
        final DBContact contact = contacts.get(0);
        if(0 == contacts.size()) {
            throw new UtGeneralException("DB contact CANNOT be verified. Contact doesn't exists", new Throwable());
        }

        final DBToken token = new DBToken();
        token.setUser(contact);
        token.setCreatedDate(new Date());
        token.setConfirmationToken(UUID.randomUUID().toString());
        dataTokenDAO.save(token);

        log.debug("DB contact %s registered", contact.toString());
        return token;
    }

    /**
     * @param tokenName
     * @return
     * @throws UtGeneralException
     */
    @Transactional
    public DBContact registerOneContact(
        final String tokenName) throws UtGeneralException {

        final List<DBToken> tokens = dataTokenDAO.findByConfirmationToken(tokenName);
        if(0 == tokens.size()) {
            throw new UtGeneralException("DB contact CANNOT be registered. Token doesn't exists", new Throwable());
        }
        final DBToken token = tokens.get(0);
        if(null == token) {
            throw new UtGeneralException("DB contact CANNOT be registered. Token doesn't exists", new Throwable());
        }

        final List<DBContact> contacts = dataContactRepo.findByEmailaddresse(token.getUser().getEmailadresse());
        final DBContact contact = contacts.get(0);
        if(null == contact) {
            throw new UtGeneralException("DB contact CANNOT be registered. Contact not found", new Throwable());
        }

        contact.setEnabled(true);
        dataContactRepo.save(contact);

        log.debug("DB contact %s registered", contact.toString());
        return contact;
    }

    /**
     * @brief edit an existing contact
     * @param cur_vorname contains current vorname value to allow search in database
     * @param cur_nachname contains current nachname value to allow search in database
     * @param new_vorname contains new vorname value to be applied
     * @param new_nachname contains new nachname value to be applied
     * @param emailadresse  contains new emailadresse value to be applied
     * @return the udpated DBContact instance
     */
    @Transactional
    public DBContact updateOneContact(
        final String cur_vorname, final String cur_nachname,
        final String new_vorname, final String new_nachname, final String emailadresse) {

        final DBAddressBook contacts = findByVorNNachname(cur_vorname, cur_nachname);
        final DBContact contact = contacts.get(0);
        if(0 == contacts.size()) {
            log.debug("Add instead of edit", contact.toString());
            return addOneContact(new_vorname, new_nachname, emailadresse, contact.isEnabled());
        }

        contact.setVorname(new_vorname);
        contact.setNachname(new_nachname);
        contact.setEmailadresse(emailadresse);

        dataContactRepo.save(contact);

        log.debug("DB contact %s updated", contact.toString());
        return contact;
    }

    /**
     * @brief delete an existing contact
     * @param vorname contains current vorname value to allow search in database
     * @param nachname contains current nachname value to allow search in database
     * @return
     */
    @Transactional
    public DBContact removeOneContact(final String vorname, final String nachname) {
        final DBAddressBook contacts = findByVorNNachname(vorname, nachname);

        final DBContact contact = contacts.get(0);
        dataContactRepo.delete(contact);

        log.debug("DB contact %s removed", contact.toString());
        return contact;
    }

    /**
     * @brief emptying DB
     */
    @Transactional
    public void deleteAllContacts() {
        dataContactRepo.deleteAll();
        log.debug("DB empty");
    }

    /************************************************************************
     LOOK UP PART
     */
    /**
     * @return
     */
    public DBAddressBook findContactById(final int id){
        final List<DBContact> contacts = dataContactRepo.findById(id);
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * @return
     */
    public DBAddressBook findContactByVorname(final String vorname){
        final List<DBContact> contacts = dataContactRepo.findByVorname(vorname);
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * @return
     */
    public DBAddressBook findContactByNachname(final String nachname){
        final List<DBContact> contacts = dataContactRepo.findByNachname(nachname);
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * @return
     */
    public DBAddressBook findByVorNNachname(final String vorname, final String nachname){
        final List<DBContact> contacts = dataContactRepo.findByVorNNachname(vorname, nachname);
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * This returns a JSON or XML with the users
     * @return text containing entries from BD
     */
    public DBAddressBook findAllContactTest() {
        final List<DBContact> contacts = dataContactRepo.findAll();
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * @return
     */
    public DBAddressBook findAllContactOrderedByIdAscending(){
        final List<DBContact> contacts = dataContactRepo.findAllOrderedByIdAscending();
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * @return
     */
    public DBAddressBook findAllContactOrderedByVornameAscending(){
        final List<DBContact> contacts = dataContactRepo.findAllOrderedByVornameAscending();
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /**
     * @return
     */
    public DBAddressBook findAllContactOrderedByNachnameAscending(){
        final List<DBContact> contacts = dataContactRepo.findAllOrderedByNachnameAscending();
        contacts.forEach((it)-> log.debug(contacts.toString()));
        return new DBAddressBook(contacts);
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param dataContactRepo autowired IDBContactDAO
     */
    public EngServiceDBABook(final IDBContactDAO dataContactRepo) {
        this.dataContactRepo = dataContactRepo;
    }

    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private IDBContactDAO dataContactRepo;

    @Autowired
    @Getter(AccessLevel.PUBLIC)
    private IDBTokenDAO dataTokenDAO;

}
