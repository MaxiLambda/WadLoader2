package lincks.maximilian.wadloader2.ddd2application.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IWadQuery implements SingleWadQuery<IWad> {
    private final IWadReadWriteRepo iWadRepo;

    public void delete(IWad iWad){
        delete(iWad);
    }

    @Override
    public Set<IWad> getByCustomTags(Set<CustomTag> tags) {
        return iWadRepo.findByCustomTagsIn(tags);
    }

    @Override
    public Optional<IWad> getById(String id) {
        return iWadRepo.findById(id);
    }

    @Override
    public List<IWad> getByDefaultTag(String path) {
        return new ArrayList<>( iWadRepo.findByDefaultTag(new DefaultTag(Path.of(path))));
    }

    public List<IWad> getAll() {
        return iWadRepo.findAll();
    }
}
