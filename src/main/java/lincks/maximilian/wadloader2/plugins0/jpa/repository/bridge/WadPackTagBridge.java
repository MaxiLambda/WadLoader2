package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.WadPackTagRepo;
import lincks.maximilian.wadloader2.domain3.tags.WadPackTag;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.WadPackTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadPackTagBridge extends AbstractBridge<WadPackTag, String> implements WadPackTagRepo {
    public WadPackTagBridge(WadPackTagSpringRepo repo) {
        super(repo);
    }
}
