package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;

import java.util.Set;

public interface WadReadWriteRepo extends AbstractReadWriteRepo<Wad, WadPath> {
    Set<Wad> findByCustomTagsIn(Set<CustomTag> tags);
}
