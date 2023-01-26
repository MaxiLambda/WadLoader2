package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadPackSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class WadPackBridge extends AbstractBridge<WadPack, String> implements WadPackRepo {
    public WadPackBridge(WadPackSpringRepo repo) {
        super(repo);
    }

    @Override
    public Set<WadPack> findByCustomTagsIn(Set<CustomTag> tags) {
        return ((WadPackSpringRepo) repo).findByCustomTagsIn(tags);
    }
}
