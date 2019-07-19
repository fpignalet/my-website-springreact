package com.core;

import com.core.eng.EEngJSONFiles;
import com.core.eng.impl.AEngJSONHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@JsonTest
@RunWith(SpringRunner.class)
public class FileJSONTests extends AEngJSONHandler {

    /*
    UNDER CONSTRUCTION
     */

    @Before
    public void setup(){
        itemOK = new DBTest();
    }

    @Test
    public void testContent() throws Exception {
        final String content = "{\"DBTests\": [{\"test\":\"" + TEST + "\"}]}";
        assertThat(this.json.parse(content)).isEqualTo(itemOK);
    }

    @Test
    public void loadFileTest() throws IOException {
        final DBTests items = (DBTests) loadFile();
        Assert.assertNotNull(items);
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0).test, TEST);
    }

    @Test
    public void saveFileTest() throws IOException {
        final DBTests items = (DBTests) loadFile();
        items.get(0).test = TESTSAVED;
        updateFile(items);
        final DBTests itemssaved = (DBTests) loadFile();
        Assert.assertNotNull(items);
        Assert.assertEquals(items.size(), 1);
        Assert.assertEquals(items.get(0).test, TESTSAVED);
    }

    @Override
    public <T> ArrayList<?> loadFile() throws IOException {
        final String data = load(EEngJSONFiles.TESTJSON.getName());
        return (DBTests) parse(data, DBTests.class, new Class[]{ DBTest.class });
    }

    @Override
    public void updateFile(final ArrayList<?> itemsDB) throws IOException {
        final String jsonString = fill(itemsDB);
        save(EEngJSONFiles.TESTJSONOUT.getName(), jsonString);
    }

    private class DBTest { public String test = TEST; }
    private class DBTests extends ArrayList<DBTest> {}

    @Autowired
    private JacksonTester<DBTest> json;

    private DBTest itemOK;

    private static final String TEST = "TEST";
    private static final String TESTSAVED = "TESTSAVED";
}
