package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WadSpringRepo extends JpaRepository<Wad, String> {
    Set<Wad> findByCustomTagsIn(Set<CustomTag> tags);
    Set<Wad> findByDefaultTag(DefaultTag tag);
}
