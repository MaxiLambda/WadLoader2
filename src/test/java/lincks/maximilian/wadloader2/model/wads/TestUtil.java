package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.domain3.wads.IWad;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge.IWadBridge;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge.WadBridge;

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
    public static List<Wad> addWadsSetup(WadBridge wadService){
        return wadPaths.stream().map(Wad::new).map(wadService::save).toList();
    }

    public static IWad addIWadSetup(IWadBridge iWadService){
        return iWadService.save(new IWad(iWadPath));
    }
}
