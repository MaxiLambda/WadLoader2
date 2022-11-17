package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.DefaultTagRepo;
import lincks.maximilian.wadloader2.domain.tags.DefaultTag;
import lincks.maximilian.wadloader2.plugins.jpa.repository.DefaultTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTagBridge extends AbstractBridge<DefaultTag, String> implements DefaultTagRepo {

    public DefaultTagBridge(DefaultTagSpringRepo repo) {
        super(repo);
    }
}
