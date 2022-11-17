package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.WadTagRepo;
import lincks.maximilian.wadloader2.domain.tags.WadTag;
import lincks.maximilian.wadloader2.plugins.jpa.repository.WadTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadTagBridge extends AbstractBridge<WadTag, String> implements WadTagRepo {
    public WadTagBridge(WadTagSpringRepo repo) {
        super(repo);
    }
}
