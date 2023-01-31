package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadPackSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class WadPackBridge extends AbstractBridge<WadPack, String, JpaRepository<WadPack, String>> implements WadPackReadWriteRepo {
    public WadPackBridge(WadPackSpringRepo repo) {
        super(repo);
    }

    @Override
    public Set<WadPack> findByCustomTagsIn(Set<CustomTag> tags) {
        return ((WadPackSpringRepo) repo).findByCustomTagsIn(tags);
    }
}
