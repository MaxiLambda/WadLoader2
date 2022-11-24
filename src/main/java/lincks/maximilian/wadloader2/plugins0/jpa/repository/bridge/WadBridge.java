package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.plugins0.jpa.repository.WadSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadBridge extends AbstractBridge<Wad, String> implements WadRepo {

    public WadBridge(WadSpringRepo repo) {
        super(repo);
    }
}
