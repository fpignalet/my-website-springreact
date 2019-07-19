package com.core;

import com.core.data.IDBContactDAO;
import com.core.data.impl.DBContact;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPADBContactTests {

    /*
    UNDER CONSTRUCTION
     */

    @Before
    public void setup(){
        contactOK = new DBContact();
        contactOK.setVorname(TESTVORNAME);
        contactOK.setNachname(TESTNACHNAME);
        contactOK.setEmailadresse(TESTEMAILADRESSE);
    }

    @Test
    public void saveTest() {
        this.entityManager.persist(contactOK);
        Assert.assertNotNull(repoContact.findByVorname(TESTVORNAME));
        Assert.assertNotNull(repoContact.findByNachname(TESTNACHNAME));
    }

    @Test
    public void editTest() {
        this.entityManager.persist(contactOK);

        DBContact contact = this.entityManager.find(DBContact.class, 0);
        Assert.assertEquals(contact.getVorname(), TESTVORNAME);
        Assert.assertEquals(contact.getNachname(), TESTNACHNAME);
        contact.setVorname(NEWVORNAME);
        contact.setNachname(NEWNACHNAME);
        this.entityManager.persist(contact);

        contact = this.entityManager.find(DBContact.class, 0);
        Assert.assertEquals(contact.getVorname(), NEWVORNAME);
        Assert.assertEquals(contact.getNachname(), NEWNACHNAME);
    }

    @Test
    public void deleteTest() {
        this.entityManager.persist(contactOK);

        DBContact contact = this.entityManager.find(DBContact.class, 1);
        Assert.assertEquals(contact.getVorname(), TESTVORNAME);
        Assert.assertEquals(contact.getNachname(), TESTNACHNAME);
        this.entityManager.remove(contact);

        contact = this.entityManager.find(DBContact.class, 1);
        Assert.assertNull(contact);
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IDBContactDAO repoContact;

    private DBContact contactOK;

    private static final String TESTVORNAME = "TESTVORNAME";
    private static final String NEWVORNAME = "NEWVORNAME";
    private static final String TESTNACHNAME = "TESTNACHNAME";
    private static final String NEWNACHNAME = "NEWNACHNAME";
    private static final String TESTEMAILADRESSE = "TESTEMAILADRESSE";

}
