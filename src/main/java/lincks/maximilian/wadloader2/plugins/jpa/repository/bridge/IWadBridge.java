package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.domain.wads.IWad;
import lincks.maximilian.wadloader2.plugins.jpa.repository.IWadSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class IWadBridge extends AbstractBridge<IWad, String> implements IWadRepo {

    public IWadBridge(IWadSpringRepo repo) {
        super(repo);
    }
}
