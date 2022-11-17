package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.WadPackTagRepo;
import lincks.maximilian.wadloader2.domain.tags.WadPackTag;
import lincks.maximilian.wadloader2.plugins.jpa.repository.WadPackTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadPackTagBridge extends AbstractBridge<WadPackTag, String> implements WadPackTagRepo {
    public WadPackTagBridge(WadPackTagSpringRepo repo) {
        super(repo);
    }
}
