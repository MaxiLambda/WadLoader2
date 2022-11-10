package lincks.maximilian.wadloader2.domain;

import lincks.maximilian.wadloader2.config.WadExtensionConfiguration;
import lincks.maximilian.wadloader2.model.wads.Wad;
import lincks.maximilian.wadloader2.utils.PathUtil;
import lincks.maximilian.wadloader2.utils.StreamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

/**
* Service used to search for Wads in the FileSystem
*/
@Service
@RequiredArgsConstructor
@Log
public class WadFileFinder {

    private final WadExtensionConfiguration wadExtCfg;

    public List<Wad> findWads(Collection<Path> paths){
        return paths.stream()
                .map(this::findWads)
                .flatMap(List::stream)
                .toList();
    }

    public List<Wad> findWads(Path path){
        List<String> allowedExtensions = wadExtCfg.getAllowed();

        try(var paths = Files.walk(path)){
            return paths.filter(StreamUtil.filter(
                    PathUtil::getExtension,
                    allowedExtensions::contains))
                    .map(Wad::new)
                    .toList();
        }catch (IOException e) {
            log.severe(e.toString());
            return List.of();
        }
    }
}
