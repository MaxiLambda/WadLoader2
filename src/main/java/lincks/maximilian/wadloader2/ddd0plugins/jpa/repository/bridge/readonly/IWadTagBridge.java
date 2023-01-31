package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge.readonly;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.interfaces.JpaTagRepository;
import lincks.maximilian.wadloader2.ddd3domain.repos.readonly.IWadTagRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.IWadTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IWadTagBridge extends AbstractReadOnlyTagBridge<IWadTag, String> implements IWadTagRepo {
    public IWadTagBridge(JpaTagRepository<IWadTag, String> repo) {
        super(repo);
    }

    @Override
    public List<IWadTag> findByNameContaining(String name) {
        return repo.findByNameContaining(name);
    }
}
