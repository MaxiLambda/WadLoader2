package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.model.tags.TagException;
import lincks.maximilian.wadloader2.repos.services.IWadService;
import lincks.maximilian.wadloader2.repos.services.WadPackService;
import lincks.maximilian.wadloader2.repos.services.WadPackTagService;
import lincks.maximilian.wadloader2.repos.services.WadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WadPackTest {
    //TODO write Test to check custom rule enforcement

    @Autowired
    WadPackService wadPackService;

    @Autowired
    WadPackTagService wadPackTagService;

    @Autowired
    WadService wadService;

    @Autowired
    IWadService iWadService;

    @AfterEach
    @BeforeEach
    void clean(){
        wadPackService.deleteAll();
        wadService.deleteAll();
        iWadService.deleteAll();
        wadPackTagService.deleteAll();
    }

    String wadPackName = "BestPackEver <3";

    @Test
    void createWadPack(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad, wadPackTagService);
        boolean allAdded = TestUtil.addWadsSetup(wadService)
                .stream()
                .map(pack::addWad)
                .reduce(true,Boolean::logicalAnd);

        wadPackService.save(pack);

        System.out.println(wadService.findAll().size());
        System.out.println(pack.allWads().size());
        //assert all Wads are added to the Wadpack
        assertTrue(allAdded);
        //+1 is due to the
        assertEquals(TestUtil.wadPaths.size()+1,pack.allWads().size());
        assertEquals(1,wadPackService.findAll().size());
    }

    @Test
    void createWadPackTwice(){
        createWadPack();
        assertThrows(TagException.class,this::createWadPack);
    }
}