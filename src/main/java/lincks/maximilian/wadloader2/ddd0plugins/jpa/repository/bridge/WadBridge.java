package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadSpringRepo;
import org.springframework.stereotype.Repository;

@Repository
public class WadBridge extends AbstractBridge<Wad, String> implements WadRepo {

    public WadBridge(WadSpringRepo repo) {
        super(repo);
    }
}
