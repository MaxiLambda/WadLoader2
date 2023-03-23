package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import jakarta.transaction.Transactional;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface WadPackSpringRepo extends JpaRepository<WadPack, WadPackName> {
    Set<WadPack> findByCustomTagsIn(Set<CustomTag> tags);

    Optional<WadPack> findByName(WadPackName name);

    @Transactional
    void deleteByName(WadPackName name);
}
