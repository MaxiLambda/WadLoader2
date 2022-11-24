package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.IWadTagRepo;
import lincks.maximilian.wadloader2.domain3.tags.IWadTag;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.IWadTagSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class IWadTagBridge extends AbstractBridge<IWadTag,String> implements IWadTagRepo {
    public IWadTagBridge(IWadTagSpringRepo repo) {
        super(repo);
    }
}
