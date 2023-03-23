package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;

import java.util.Optional;
import java.util.Set;

public interface WadPackReadWriteRepo extends AbstractReadWriteRepo<WadPack, WadPackName> {
    Set<WadPack> findByCustomTagsIn(Set<CustomTag> tags);

    void deleteByName(WadPackName name);
    Optional<WadPack> findByName(WadPackName name);
}
