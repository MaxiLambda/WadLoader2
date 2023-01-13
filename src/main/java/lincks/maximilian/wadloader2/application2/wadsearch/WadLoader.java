package lincks.maximilian.wadloader2.application2.wadsearch;

import lincks.maximilian.wadloader2.domain3.repos.IWadRepo;
import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.wads.IWad;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WadLoader {

    private final WadFileFinder wadFinder;

    private final IWadRepo iWadRepo;
    private final WadRepo wadRepo;

    public List<Wad> loadWads(List<Path> path){
        return wadFinder.findWads(path)
                .stream()
                .map(wadRepo::save)
                .toList();
    }

    public List<IWad> loadIWads(List<Path> path){
        return wadFinder.findIWads(path)
                .stream()
                .map(iWadRepo::save)
                .toList();
    }

}
