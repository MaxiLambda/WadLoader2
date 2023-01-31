package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.Set;

public interface WadPackReadWriteRepo extends AbstractReadWriteRepo<WadPack, String> {
    Set<WadPack> findByCustomTagsIn(Set<CustomTag> tags);
}
