package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.repos.services.WadService;

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

    /**
     * Tries to add two Wads to the database
     */
    public static void addWadsSetup(WadService wadService){
        wadPaths.stream().map(Wad::new).forEach(wadService::save);
    }
}
