package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TestUtil {

    public final static List<Path> wadPaths = Stream.of(
                    "D:\\Doom\\the\\only\\thing\\they\\fear\\is\\you.wad",
                    "D:\\Doom\\ssg\\ssg.pk3"
            ).map(Path::of)
            .map(Path::toAbsolutePath)
            .toList();

    public final static Path iWadPath = Path.of("D:\\Doom\\iwads\\doom666.wad");

    /**
     * Tries to add two Wads to the database
     */
    public static List<Wad> addWadsSetup(WadReadWriteRepo wadService){
        return wadPaths.stream().map(Wad::new).map(wadService::save).toList();
    }

    public static IWad addIWadSetup(IWadReadWriteRepo iWadService){
        return iWadService.save(new IWad(iWadPath));
    }

    public static WadPackName wadPackName = new WadPackName("TestPack");
    public static WadPack getWadPack(WadPackReadWriteRepo wadPackRepo){
        return new WadPack(wadPackName,new IWad(iWadPath),wadPackRepo);
    }
}
