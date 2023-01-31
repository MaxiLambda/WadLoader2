package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WadPackQuery implements WadConfigQuery<WadPack> {
    private final WadPackReadWriteRepo wadPackRepo;

    public void delete(WadPack wadPack){
        wadPackRepo.delete(wadPack);
    }

    @Override
    public Set<WadPack> getByCustomTags(Set<CustomTag> tags) {
        return wadPackRepo.findByCustomTagsIn(tags);
    }

    @Override
    public List<WadPack> getByTag(Tag tag) {
        return WadConfigQuery.super.getByTag(tag);
    }

    public List<WadPack> getAll() {
        return wadPackRepo.findAll();
    }
}
