package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class WadBridge extends AbstractBridge<Wad, String> implements WadRepo {

    public WadBridge(WadSpringRepo repo) {
        super(repo);
    }

    @Override
    public Set<Wad> findByCustomTagsIn(Set<CustomTag> tags) {
        return ((WadSpringRepo) repo).findByCustomTagsIn(tags);
    }

    @Override
    public Set<Wad> findByDefaultTag(DefaultTag tag) {
        return ((WadSpringRepo) repo).findByDefaultTag(tag);
    }
}
