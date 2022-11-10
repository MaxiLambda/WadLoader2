package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.TagException;
import lincks.maximilian.wadloader2.repos.services.WadPackService;
import lincks.maximilian.wadloader2.repos.services.WadPackTagService;
import lincks.maximilian.wadloader2.repos.services.WadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WadPackTest {

    //TODO write Test to check for the usage of exactly one IWAD

    //TODO write Test to check custom rule enforcement

    @Autowired
    WadPackService wadPackService;

    @Autowired
    WadPackTagService wadPackTagService;

    @Autowired
    WadService wadService;

    @BeforeEach
    void setup(){
        wadPackService.deleteAll();
        wadService.deleteAll();
    }

    String wadPackName = "BestPackEver <3";

    @Test
    void createWadPack(){
        WadPack pack = new WadPack(wadPackName, wadPackTagService);
        boolean allAdded = TestUtil.addWadsSetup(wadService)
                .stream()
                .map(pack::addWad)
                .reduce(true,Boolean::logicalAnd);

        wadPackService.save(pack);

        System.out.println(wadService.findAll().size());
        System.out.println(pack.getWads().size());
        //assert all Wads are added to the Wadpack
        assertTrue(allAdded);
        assertEquals(pack.wads().size(),TestUtil.wadPaths.size());
        assertEquals(wadPackService.findAll().size(),1);
    }

    @Test
    void createWadPackTwice(){
        createWadPack();
        assertThrows(TagException.class,this::createWadPack);
    }
}