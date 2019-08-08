package com.mod.soap;

import com.srv.xml.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ItemRepository {

    private static final Map<String, Item> items = new HashMap<>();

    @PostConstruct
    public void initData() {

        Item student = new Item();
        student.setField1("Field11");
        student.setField2("Field12");
        items.put(student.getField1(), student);

        student = new Item();
        student.setField1("Field21");
        student.setField2("Field22");
        items.put(student.getField1(), student);

        student = new Item();
        student.setField1("Field31");
        student.setField2("Field32");
        items.put(student.getField1(), student);

        student = new Item();
        student.setField1("Field41");
        student.setField2("Field42");
        items.put(student.getField1(), student);
    }

    public Item findItem(String name) {
        Assert.notNull(name, "The Student's name must not be null");
        return items.get(name);
    }
}
