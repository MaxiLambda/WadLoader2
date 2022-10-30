package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.repos.services.WadService;
import lincks.maximilian.wadloader2.repos.services.WadTagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static lincks.maximilian.wadloader2.model.wads.TestUtil.addWadsSetup;
import static lincks.maximilian.wadloader2.model.wads.TestUtil.wadPaths;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WadTest {

    @Autowired
    WadService wadService;
    @Autowired
    WadTagService wadTagService;



    @BeforeEach
    void beforeAll() {
        wadService.deleteAll();
        wadTagService.deleteAll();
    }

    @Test
    void addWadsToDB() {
        addWadsSetup(wadService);

        //check if all wads were added
        assertEquals(wadService.findAll().size(),wadPaths.size());
        //check if paths match
        assertEquals(
                wadService.findById(wadPaths.get(0).toString()).getPath()
                ,wadPaths.get(0).toString()
        );
        //check if WadTags were created and persisted
        assertEquals(wadTagService.findAll().size(),wadPaths.size());
    }

    @Test
    void addWadsTwice(){
        //checks if wads are just added once
        addWadsSetup(wadService);
        addWadsSetup(wadService);

        assertEquals(wadService.findAll().size(), wadPaths.size());
    }


}