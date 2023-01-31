package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;

import java.util.Set;

public interface WadReadWriteRepo extends AbstractReadWriteRepo<Wad, String> {
    Set<Wad> findByCustomTagsIn(Set<CustomTag> tags);
    Set<Wad> findByDefaultTag(DefaultTag tags);

}
