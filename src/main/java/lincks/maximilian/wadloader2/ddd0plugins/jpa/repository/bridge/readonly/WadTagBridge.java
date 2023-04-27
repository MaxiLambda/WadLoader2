package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge.readonly;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.interfaces.JpaTagRepository;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.WadTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.WadTag;
import org.springframework.stereotype.Repository;

@Repository
public class WadTagBridge extends AbstractReadOnlyTagBridge<WadTag, String> implements WadTagRepo {
    public WadTagBridge(JpaTagRepository<WadTag, String> repo) {
        super(repo);
    }
}
