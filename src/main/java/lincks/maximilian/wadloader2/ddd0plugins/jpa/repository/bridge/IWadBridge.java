package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.IWadSpringRepo;
import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class IWadBridge extends AbstractBridge<IWad, String> implements IWadRepo {

    public IWadBridge(IWadSpringRepo repo) {
        super(repo);
    }

    @Override
    public Set<IWad> findByCustomTagsIn(Set<CustomTag> tags) {
        return ((IWadSpringRepo) repo).findByCustomTagsIn(tags);
    }

    @Override
    public Set<IWad> findByDefaultTag(DefaultTag tag) {
        return ((IWadSpringRepo) repo).findByDefaultTag(tag);
    }
}
