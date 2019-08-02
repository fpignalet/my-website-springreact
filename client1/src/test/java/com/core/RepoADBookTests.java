package com.core;

import com.core.data.IDBContactDAO;
import com.core.data.impl.sql.DBAddressBook;
import com.core.data.impl.sql.DBContact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoADBookTests {

    @Autowired
    private IDBContactDAO userRepository;

    @Test
    public void saveTest() {
        DBContact contact = new DBContact();
        contact.setVorname(TESTVORNAME);
        contact.setNachname(TESTNACHNAME);
        contact.setEmailadresse(TESTEMAILADRESSE);

        userRepository.save(contact);

        Assert.assertNotNull(userRepository.findByNachname(TESTNACHNAME));
    }

    @Test
    public void editTest() {
        final List<DBContact> items = userRepository.findByNachname(TESTNACHNAME);

        final DBAddressBook contacts = new DBAddressBook(items);
        final DBContact contact = contacts.get(0);
        contact.setNachname(NEWNACHNAME);
        userRepository.save(contact);

        Assert.assertNotNull(userRepository.findByVorname(TESTVORNAME));
        Assert.assertNotNull(userRepository.findByNachname(NEWNACHNAME));
    }

    @Test
    public void deleteTest() {
        final List<DBContact> items = userRepository.findByNachname(TESTNACHNAME);

        final DBAddressBook contacts = new DBAddressBook(items);
        final DBContact contact = contacts.get(0);
        userRepository.delete(contact);

        final List<DBContact> nothing = userRepository.findByNachname(TESTNACHNAME);
        Assert.assertNull(nothing);
    }

    private static final String TESTVORNAME = "TESTVORNAME";
    private static final String NEWVORNAME = "NEWVORNAME";
    private static final String TESTNACHNAME = "TESTNACHNAME";
    private static final String NEWNACHNAME = "NEWNACHNAME";
    private static final String TESTEMAILADRESSE = "TESTEMAILADRESSE";
}
