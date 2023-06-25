package com.kevinsa.security.service.cpompent.utils;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kevinsa.security.service.Application;
import com.kevinsa.security.service.utils.JsonHierarchyParseExtractor;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class JsonExecutorTest {

    @Autowired
    private JsonHierarchyParseExtractor jsonHierarchyParseExtractor;

    @Test
    public void getKeyTest() {
        String jsonString1 = "{\"person\": {\"name\": \"kevinsa\", \"age\": 20}}";
        List<String> results = jsonHierarchyParseExtractor.getJsonKey(jsonString1, "", null);
        Assert.assertEquals("person.name", results.get(0));
        String jsonString2 = "{\"class\": [{\"person\": {\"name\": \"kevinsa\", \"age\": 20}}, {\"person\": {\"name\": \"kevinsa\", \"age\": 20}}]}";
        List<String> results1 = jsonHierarchyParseExtractor.getJsonKey(jsonString2, "", null);
        Assert.assertEquals("class[0].person.name", results1.get(0));
        List<String> results2 = jsonHierarchyParseExtractor.getJsonKey(jsonString2, "", 1);
        Assert.assertEquals("class[0].person.name", results2.get(0));
        Assert.assertEquals(2, results2.size());

    }
}
