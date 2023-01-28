package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.exception.WadPackTagException;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackAddException;
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
    WadRepo wadService;

    @Autowired
    IWadRepo iWadService;

//    @AfterEach
    @BeforeEach
    void clean(){
        wadPackService.deleteAll();
        wadService.deleteAll();
        iWadService.deleteAll();
    }

    String wadPackName = "BestPackEver <3";

    @Test
    void createWadPack(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad, wadPackService);

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
        assertThrows(WadPackTagException.class,this::createWadPack);
    }

    @Test
    void addWadToPackWithMaxInt(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad, wadPackService);
        List<Wad> wads = TestUtil.addWadsSetup(wadService);


        pack.setWads(Map.of(Integer.MAX_VALUE, wads.get(0).getPath()));

        Wad wad = wads.get(1);
        assertThrows(WadPackAddException.class,() -> pack.addWad(wad));
    }

    @Test
    void addWadToPack(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad, wadPackService);
        List<Wad> wads = TestUtil.addWadsSetup(wadService);

        pack.setWads(Map.of(1, wads.get(0).getPath()));
        pack.addWad(wads.get(1));

        assertEquals(3, pack.allWadIds().size());
    }
}