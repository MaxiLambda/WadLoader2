package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.exception.WadPackTagException;
import lincks.maximilian.wadloader2.ddd3domain.wads.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
class WadPackTest {

    @Autowired
    WadPackReadWriteRepo wadPackService;

    @Autowired
    WadReadWriteRepo wadService;

    @Autowired
    IWadReadWriteRepo iWadService;

//    @AfterEach
    @BeforeEach
    void clean(){
        wadPackService.deleteAll();
        wadService.deleteAll();
        iWadService.deleteAll();
    }

    WadPackName wadPackName = new WadPackName("BestPackEver <3");

    @Test
    void createWadPack(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad.getPath(), wadPackService);

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
        WadPack pack = new WadPack(wadPackName,iWad.getPath(), wadPackService);
        List<Wad> wads = TestUtil.addWadsSetup(wadService);


        pack.setWads(Map.of(Integer.MAX_VALUE, wads.get(0).getPath()));

        Wad wad = wads.get(1);
        assertThrows(WadPackAddException.class,() -> pack.addWad(wad));
    }

    @Test
    void addWadToPack(){
        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPack pack = new WadPack(wadPackName,iWad.getPath(), wadPackService);
        List<Wad> wads = TestUtil.addWadsSetup(wadService);

        pack.setWads(Map.of(1, wads.get(0).getPath()));
        pack.addWad(wads.get(1));

        assertEquals(3, pack.allWadIds().size());
    }

    @Test
    void changeWads(){
        List<Wad> wads1 = TestUtil.addWadsSetup(wadService);
        List<Wad> wads2 = TestUtil.addWadsSetup2(wadService);
//        IWad iWad = TestUtil.addIWadSetup(iWadService);
        WadPath wad1a = wads1.get(0).getPath();
        WadPath wad1b = wads1.get(1).getPath();
        WadPath wad2a = wads2.get(0).getPath();
        WadPath wad2b = wads2.get(1).getPath();

        WadPack pack = TestUtil.getWadPack(wadPackService);

        //empty pack
        wadPackService.save(pack);
        assertEquals(1, pack.allWadIds().size());

        //add two wads
        pack.setWads(Map.of(0,wad1a,1,wad1b));
        wadPackService.save(pack);
        assertEquals(3,pack.allWadIds().size());
        assert pack.allWadIds().containsAll(List.of(wad1a.getPath(),wad1b.getPath()));

        //remove one
        pack.setWads(Map.of(0,wad1b));
        wadPackService.save(pack);
        assertEquals(2,pack.allWadIds().size());
        assert pack.allWadIds().contains(wad1b.getPath());

        //add two new ones
        pack.setWads(Map.of(0,wad1b,1,wad2a,2,wad2b));
        wadPackService.save(pack);
        assertEquals(4, pack.allWadIds().size());
        assert pack.allWadIds().containsAll(List.of(wad1b.getPath(), wad2a.getPath(),wad2b.getPath()));
    }
}