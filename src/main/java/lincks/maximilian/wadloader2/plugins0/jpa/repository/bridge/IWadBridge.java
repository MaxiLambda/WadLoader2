package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.IWadRepo;
import lincks.maximilian.wadloader2.domain3.wads.IWad;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.IWadSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class IWadBridge extends AbstractBridge<IWad, String> implements IWadRepo {

    public IWadBridge(IWadSpringRepo repo) {
        super(repo);
    }
}
