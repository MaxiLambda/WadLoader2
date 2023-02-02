package lincks.maximilian.wadloader2.ddd2application.wadsearch;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WadLoader {

    private final WadFileFinder wadFinder;
    private final IWadReadWriteRepo iWadRepo;
    private final WadReadWriteRepo wadRepo;

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
