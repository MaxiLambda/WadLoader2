package lincks.maximilian.wadloader2.model.tags;

import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static lincks.maximilian.wadloader2.model.wads.TestUtil.addWadsSetup;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomTagTest {

    @Autowired
    WadRepo wadService;
    @Autowired
    CustomTagRepo customTagService;

    String customTagName = "R2D2";
    @BeforeEach
    void setUp(){
        wadService.deleteAll();
        customTagService.deleteAll();
    }

    @BeforeAll
    static void staticSetup(){
        System.setProperty("java.awt.headless", "false");
    }
    @Test
    void addCustomTag(){
        Wad wad = addWadsSetup(wadService).get(0);
        wad.addCustomTag(customTagName);
        wadService.save(wad);
        //is not added again
        assertFalse(wad.addCustomTag(customTagName));

        assertEquals(1, wad.getCustomTags().size());

        assertTrue(customTagService.exists(customTagName));

    }

    @Test
    void findByCustomTag(){
        Wad wad = addWadsSetup(wadService).get(0);
        wad.addCustomTag(customTagName);
        wadService.save(wad);

        assertTrue(customTagService.exists(customTagName));
        CustomTag t = customTagService.findById(customTagName).get();

        assertEquals(1,wadService.findByCustomTagsIn(Set.of(t)).size());

    }
}