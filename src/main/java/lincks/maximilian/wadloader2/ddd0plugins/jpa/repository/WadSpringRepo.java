package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WadSpringRepo extends JpaRepository<Wad, WadPath> {
    Set<Wad> findByCustomTagsIn(Set<CustomTag> tags);
}
