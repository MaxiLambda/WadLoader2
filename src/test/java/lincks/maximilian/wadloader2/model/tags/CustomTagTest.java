package lincks.maximilian.wadloader2.model.tags;

import lincks.maximilian.wadloader2.model.wads.Wad;
import lincks.maximilian.wadloader2.repos.services.CustomTagService;
import lincks.maximilian.wadloader2.repos.services.WadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static lincks.maximilian.wadloader2.model.wads.TestUtil.addWadsSetup;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomTagTest {

    @Autowired
    WadService wadService;
    @Autowired
    CustomTagService customTagService;

    String customTagName = "R2D2";
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
}