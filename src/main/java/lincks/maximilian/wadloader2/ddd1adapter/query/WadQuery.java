package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WadQuery implements SingleWadQuery<Wad> {

    private final WadReadWriteRepo wadRepo;

    public List<Wad> getAll() {
        return wadRepo.findAll();
    }

    @Override
    public Optional<Wad> getById(String id) {
        return wadRepo.findById(id);
    }

    @Override
    public List<Wad> getByDefaultTag(String path) {
        return new ArrayList<>(wadRepo.findByDefaultTag(new DefaultTag(Path.of(path))));
    }

    public void delete(Wad wad){
        wadRepo.delete(wad);
    }

    @Override
    public Set<Wad> getByCustomTags(Set<CustomTag> tags) {
        return wadRepo.findByCustomTagsIn(tags);
    }

    public List<Wad> getAllByWadPack(WadPack pack){
        return getById(pack.allWadIds());
    }
}
