package lincks.maximilian.wadloader2.plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain.repos.WadPackRepo;
import lincks.maximilian.wadloader2.domain.wads.WadPack;
import lincks.maximilian.wadloader2.plugins.jpa.repository.WadPackSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadPackBridge extends AbstractBridge<WadPack, String> implements WadPackRepo {
    public WadPackBridge(WadPackSpringRepo repo) {
        super(repo);
    }
}
