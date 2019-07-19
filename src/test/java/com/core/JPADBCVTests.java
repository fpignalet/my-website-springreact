package com.core;

import com.core.data.IDBHistContenerDAO;
import com.core.data.impl.DBHistContener;
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
public class JPADBCVTests {

    /*
    UNDER CONSTRUCTION
     */

    @Autowired
    private TestEntityManager entityManager;

	@Autowired
	private IDBHistContenerDAO repoCV;

    private DBHistContener contenerOK;

    @Before
    public void setup(){
        contenerOK = new DBHistContener();
        contenerOK.setContenername(TESTCVENTRY);
    }

    @Test
    public void saveTest() {
        this.entityManager.persist(contenerOK);
        Assert.assertNotNull(repoCV.findByName(TESTCVENTRY));
    }

    @Test
    public void editTest() {
        this.entityManager.persist(contenerOK);

        DBHistContener contener = this.entityManager.find(DBHistContener.class, 1);
        Assert.assertEquals(contener.getContenername(), TESTCVENTRY);
        contener.setContenername(NEWCVENTRY);
        this.entityManager.persist(contener);

        contener = this.entityManager.find(DBHistContener.class, 1);
        Assert.assertEquals(contener.getContenername(), NEWCVENTRY);
    }

    @Test
    public void deleteTest() {
        this.entityManager.persist(contenerOK);

        DBHistContener contener = this.entityManager.find(DBHistContener.class, 1);
        Assert.assertEquals(contener.getContenername(), TESTCVENTRY);
        this.entityManager.remove(contener);

        contener = this.entityManager.find(DBHistContener.class, 1);
        Assert.assertNull(contener);
    }

    private static final String TESTCVENTRY = "TESTCVENTRY";
    private static final String NEWCVENTRY = "NEWCVENTRY";
}
