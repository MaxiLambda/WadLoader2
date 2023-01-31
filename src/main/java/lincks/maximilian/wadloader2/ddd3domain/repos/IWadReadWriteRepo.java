package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;

import java.util.Set;

public interface IWadReadWriteRepo extends AbstractReadWriteRepo<IWad, String> {
    Set<IWad> findByCustomTagsIn(Set<CustomTag> tags);
    Set<IWad> findByDefaultTag(DefaultTag tag);
}
