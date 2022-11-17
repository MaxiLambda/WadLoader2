package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.IWadTagRepo;
import lincks.maximilian.wadloader2.domain.tags.IWadTag;
import lincks.maximilian.wadloader2.plugins.jpa.repository.IWadTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class IWadTagBridge extends AbstractBridge<IWadTag,String> implements IWadTagRepo {
    public IWadTagBridge(IWadTagSpringRepo repo) {
        super(repo);
    }
}
