package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.DefaultTagRepo;
import lincks.maximilian.wadloader2.domain3.tags.DefaultTag;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.DefaultTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTagBridge extends AbstractBridge<DefaultTag, String> implements DefaultTagRepo {

    public DefaultTagBridge(DefaultTagSpringRepo repo) {
        super(repo);
    }
}
