package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge.readonly;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.interfaces.JpaTagRepository;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.WadPackTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.WadPackTag;
import org.springframework.stereotype.Repository;

@Repository
public class WadPackTagBridge extends AbstractReadOnlyTagBridge<WadPackTag, String> implements WadPackTagRepo {
    public WadPackTagBridge(JpaTagRepository<WadPackTag, String> repo) {
        super(repo);
    }
}
