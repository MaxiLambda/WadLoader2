package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface WadPackSpringRepo extends JpaRepository<WadPack, WadPackName> {
    Set<WadPack> findByCustomTagsIn(Set<CustomTag> tags);
}
