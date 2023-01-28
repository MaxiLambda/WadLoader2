package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge.readonly;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.readonly.JpaTagRepository;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.CustomTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTagBridge extends AbstractReadOnlyTagBridge<CustomTag, String> implements CustomTagRepo {
    public CustomTagBridge(JpaTagRepository<CustomTag, String> repo) {
        super(repo);
    }
}
