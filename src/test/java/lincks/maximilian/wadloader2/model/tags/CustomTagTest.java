package lincks.maximilian.wadloader2.model.tags;

import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static lincks.maximilian.wadloader2.model.wads.TestUtil.addWadsSetup;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomTagTest {

    @Autowired
    WadRepo wadService;
    @Autowired
    CustomTagRepo customTagService;

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