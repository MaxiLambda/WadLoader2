package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.IWadSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class IWadBridge extends AbstractBridge<IWad, String> implements IWadRepo {

    public IWadBridge(IWadSpringRepo repo) {
        super(repo);
    }
}
