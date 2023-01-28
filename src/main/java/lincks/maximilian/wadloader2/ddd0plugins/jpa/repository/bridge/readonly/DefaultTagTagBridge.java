package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge.readonly;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.readonly.JpaTagRepository;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.DefaultTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultTagTagBridge extends AbstractReadOnlyTagBridge<DefaultTag, String> implements DefaultTagRepo {

    public DefaultTagTagBridge(JpaTagRepository<DefaultTag, String> repo) {
        super(repo);
    }
}
