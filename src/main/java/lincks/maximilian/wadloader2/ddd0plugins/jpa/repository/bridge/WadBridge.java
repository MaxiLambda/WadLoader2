package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class WadBridge extends AbstractBridge<Wad, WadPath, JpaRepository<Wad, WadPath>> implements WadReadWriteRepo {

    public WadBridge(WadSpringRepo repo) {
        super(repo);
    }

    @Override
    public Set<Wad> findByCustomTagsIn(Set<CustomTag> tags) {
        return ((WadSpringRepo) repo).findByCustomTagsIn(tags);
    }
}
