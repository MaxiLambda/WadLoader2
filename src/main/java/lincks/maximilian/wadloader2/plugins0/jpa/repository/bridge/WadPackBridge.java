package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.WadPackRepo;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.WadPackSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadPackBridge extends AbstractBridge<WadPack, String> implements WadPackRepo {
    public WadPackBridge(WadPackSpringRepo repo) {
        super(repo);
    }
}
