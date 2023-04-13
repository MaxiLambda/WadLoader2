package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static lincks.maximilian.wadloader2.model.wads.TestUtil.addWadsSetup;
import static lincks.maximilian.wadloader2.model.wads.TestUtil.wadPaths;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
class WadTest {

    @Autowired
    WadReadWriteRepo wadService;

    @Autowired
    CustomTagReadWriteRepo customTagService;

    @AfterEach
    @BeforeEach
    void cleanUp() {
        wadService.deleteAll();
    }

    @Test
    void addWadsToDB() {
        addWadsSetup(wadService);

        //check if all wads were added
        assertEquals(wadService.findAll().size(),wadPaths.size());
        //check if paths match
        assertEquals(
                wadService.findById(new WadPath(wadPaths.get(0).toString())).get().getPath()
                ,new WadPath(wadPaths.get(0).toString())
        );
    }

    @Test
    void addWadsTwice(){
        //checks if wads are just added once to the DB
        addWadsSetup(wadService);
        addWadsSetup(wadService);

        assertEquals(wadService.findAll().size(), wadPaths.size());
    }
}