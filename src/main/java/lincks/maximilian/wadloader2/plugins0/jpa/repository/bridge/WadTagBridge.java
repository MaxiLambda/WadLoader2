package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.WadTagRepo;
import lincks.maximilian.wadloader2.domain3.tags.WadTag;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.WadTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadTagBridge extends AbstractBridge<WadTag, String> implements WadTagRepo {
    public WadTagBridge(WadTagSpringRepo repo) {
        super(repo);
    }
}
