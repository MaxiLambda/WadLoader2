package lincks.maximilian.wadloader2.ddd2application.wadsearch;

import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.SingleWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd4abstraction.PathUtil;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
* Service used to search for Wads in the FileSystem
*/
@Service
@RequiredArgsConstructor
@Log
public class WadFileFinder {

    private final AllowedWadExtensions wadExtCfg;

    public List<Wad> findWads(Collection<Path> paths){
        return findWadElements(paths, Wad::new);
    }

    public List<Wad> findWads(Path path){
        return findWadElements(path, Wad::new);
    }

    public List<IWad> findIWads(Collection<Path> paths){
        return findWadElements(paths, IWad::new);
    }

    private <T extends SingleWad> List<T> findWadElements(Collection<Path> paths, Function<Path,T> mapper){
        return paths.stream()
                .map(path -> findWadElements(path,mapper))
                .flatMap(List::stream)
                .toList();
    }

    private <T extends SingleWad> List<T> findWadElements(Path path, Function<Path,T> mapper) {
        List<String> allowedExtensions = wadExtCfg.getAllowed();

        try(var paths = Files.walk(path)){
            return paths.filter(StreamUtil.filter(
                    PathUtil::getExtension,
                    allowedExtensions::contains))
                    .map(mapper)
                    .toList();
        }catch (IOException e) {
            log.severe(e.toString());
            return List.of();
        }
    }
}
