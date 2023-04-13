package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWadPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IWadSpringRepo extends JpaRepository<IWad, IWadPath> {
    Set<IWad> findByCustomTagsIn(Set<CustomTag> tags);
    Set<IWad> findByDefaultTag(DefaultTag tag);
}
