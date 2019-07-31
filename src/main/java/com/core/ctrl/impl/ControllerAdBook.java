package com.core.ctrl.impl;

import com.core.ctrl.AControllerBase;
import com.core.data.impl.sql.DBAddressBook;
import com.core.data.impl.sql.DBContact;
import com.core.data.impl.sql.DBToken;
import com.core.eng.impl.EngServiceDBABook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * used from React module module_adbook.jsx
 */
@Slf4j
@RestController
@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
public class ControllerAdBook extends AControllerBase {

    /************************************************************************
     PUBLIC IMPLEM PART:
     */

    @RequestMapping(value="/addressbook_list", method = RequestMethod.GET)
    @CrossOrigin
    public String addressbook_list() {
        try {
            log.info("ACTION ADBOOK GET LIST");
            return getResult(null);
        } catch (Exception e) {
            return getError(e);
        }
    }

    @RequestMapping(value="/addressbook_add", method = RequestMethod.POST)
    @CrossOrigin
    public String addressbook_add(
        @RequestParam("vorname") final String vorname,
        @RequestParam("nachname") final String nachname,
        @RequestParam("email") final String email,
        final Model model) {
        try {
            final DBContact contact = getEngineContact().addOneContact(vorname, nachname, email, true);
            log.info("ACTION ADD: " + contact.toString());

            return getResult(null);
        } catch (Exception e) {
            return getError(e);
        }
    }

    @RequestMapping(value="/addressbook_edit", method = RequestMethod.POST)
    @CrossOrigin
    public String addressbook_edit(
        @RequestParam("id") final int id,
        @RequestParam("cur_vorname") final String cur_vorname,
        @RequestParam("cur_nachname") final String cur_nachname,
        @RequestParam("new_vorname") final String new_vorname,
        @RequestParam("new_nachname") final String new_nachname,
        @RequestParam("email") final String email,
        final Model model) {
        try {
            final DBContact contact = getEngineContact().updateOneContact(cur_vorname, cur_nachname, new_vorname, new_nachname, email);
            log.info("ACTION EDIT: " + contact.toString());

            return getResult(null);
        } catch (Exception e) {
            return getError(e);
        }
    }

    @RequestMapping(value="/addressbook_remove", method = RequestMethod.POST)
    @CrossOrigin
    public String addressbook_remove(
        @RequestParam("id") final int id,
        @RequestParam("vorname") final String vorname,
        @RequestParam("nachname") final String nachname,
        final Model model) {
        try {
            final DBContact contact = getEngineContact().removeOneContact(vorname, nachname);
            log.info("ACTION REMOVE: " + contact.toString());

            return getResult(null);
        } catch (Exception e) {
            return getError(e);
        }
    }

    /**
     * @param vorname
     * @param nachname
     * @param email
     * @param modelAndView
     * @return
     */
    @RequestMapping(value="/addressbook_reg", method = RequestMethod.POST)
    @CrossOrigin
    public String registerUser(
        @RequestParam("vorname") final String vorname,
        @RequestParam("nachname") final String nachname,
        @RequestParam("email") final String email,
        final Model modelAndView
    ){
        try {
            final DBContact contact = getEngineContact().addOneContact(vorname, nachname, email, false);
            final DBToken token = getEngineContact().verifyOneContact(contact.getVorname(), contact.getNachname());
            getEngineMail().sendRegistrationMessage(contact.getEmailadresse(), token.getConfirmationToken());

            return getResult("A verification email has been sent to: " + contact.getEmailadresse());
        } catch (Exception e) {
            return getError(e);
        }
    }

    /**
     * @param token
     * @param modelAndView
     * @return
     */
    @RequestMapping(value="/addressbook_confirm", method= {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin
    public String confirmUserAccount(
        final @RequestParam("token")String token,
        final Model modelAndView
    ) {
        try {
            getEngineContact().registerOneContact(token);

            return getResult("Congratulations! Email is verified, this account has been activated!");
        } catch (Exception e) {
            return getError(e);
        }
    }

    /************************************************************************
     INNER IMPLEM PART:
     */

    /**
     * @return
     * @throws IOException
     */
    protected String getResult(final String message) throws IOException {
        DBAddressBook contacts = getEngineContact().findAllContactOrderedByIdAscending();
        if(0 == contacts.size()){
            contacts = (DBAddressBook) getEngineContact().fromItems2DB();
        }

        if(null != message) {
            final String jsonString = getEngineContact().fill(contacts);
            return "{ \"message\": \"" + message + "\", \"result\": " + jsonString + " }";
        }
        else {
            final String jsonString = getEngineContact().fill(contacts);
            return "{ \"result\": " + jsonString + " }";
        }
    }

    /**
     * @return
     * @throws IOException
     */
    protected String getError(final Exception e) {
        log.debug(e.getStackTrace().toString());
        return "{ \"error\": \"" + e.getMessage() + "\" }";
    }

    /************************************************************************
     INIT PART
     */
    /**
     * @brief constructor
     * @param engineContact autowired EngServiceDBABook
     */
    public ControllerAdBook(final EngServiceDBABook engineContact) {
        super(null, null, engineContact, null);
    }

}
