package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.CustomTagRepo;
import lincks.maximilian.wadloader2.domain.tags.CustomTag;
import lincks.maximilian.wadloader2.plugins.jpa.repository.CustomTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTagBridge extends AbstractBridge<CustomTag, String> implements CustomTagRepo {

    public CustomTagBridge(CustomTagSpringRepo repo) {
        super(repo);
    }
}
