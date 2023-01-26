package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;

import java.util.List;
import java.util.Set;

public interface WadRepo extends AbstractRepo<Wad, String> {
    List<Wad> findByCustomTagsIn(Set<CustomTag> tags);
}
