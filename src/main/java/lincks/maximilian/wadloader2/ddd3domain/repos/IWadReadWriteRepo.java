package lincks.maximilian.wadloader2.ddd3domain.repos;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWadPath;

import java.util.Set;

public interface IWadReadWriteRepo extends AbstractReadWriteRepo<IWad, IWadPath> {
    Set<IWad> findByCustomTagsIn(Set<CustomTag> tags);
    Set<IWad> findByDefaultTag(DefaultTag tag);
}
