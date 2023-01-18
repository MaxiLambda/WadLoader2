package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.CustomTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTagBridge extends AbstractBridge<CustomTag, String> implements CustomTagRepo {

    public CustomTagBridge(CustomTagSpringRepo repo) {
        super(repo);
    }
}
