package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.CustomTagRepo;
import lincks.maximilian.wadloader2.domain3.tags.CustomTag;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.CustomTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTagBridge extends AbstractBridge<CustomTag, String> implements CustomTagRepo {

    public CustomTagBridge(CustomTagSpringRepo repo) {
        super(repo);
    }
}
