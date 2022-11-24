package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.domain3.repos.IWadRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadPackRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadPackTagRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.tags.exception.TagException;
import lincks.maximilian.wadloader2.domain3.wads.IWad;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;
import lincks.maximilian.wadloader2.domain3.wads.WadPackAddException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WadPackTest {
    //TODO write Test to check custom rule enforcement

    @Autowired
    WadPackRepo wadPackService;

    @Autowired
    WadPackTagRepo wadPackTagService;

    @Autowired
    WadRepo wadService;

    @Autowired
    IWadRepo iWadService;

//    @AfterEach
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

        TestUtil.addWadsSetup(wadService)
                .forEach(pack::addWad);

        wadPackService.save(pack);

        wadPackService.save(pack);

        //+1 is due to the IWAD
        assertEquals(TestUtil.wadPaths.size()+1,pack.allWadIds().size());
        assertEquals(1,wadPackService.findAll().size());
    }

    @Test
    void createWadPackTwice(){
        createWadPack();
        assertThrows(TagException.class,this::createWadPack);
    }

    @Test
    void addWadToPackWithMaxInt(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad, wadPackTagService);
        List<Wad> wads = TestUtil.addWadsSetup(wadService);


        pack.setWads(Map.of(Integer.MAX_VALUE, wads.get(0).getPath()));

        assertThrows(WadPackAddException.class,() -> pack.addWad(wads.get(1)));
    }

    @Test
    void addWadToPack(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad, wadPackTagService);
        List<Wad> wads = TestUtil.addWadsSetup(wadService);

        pack.setWads(Map.of(1, wads.get(0).getPath()));
        pack.addWad(wads.get(1));

        assertEquals(3, pack.allWadIds().size());
    }
}