package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.WadPackSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WadPackBridge extends AbstractBridge<WadPack, WadPackName, JpaRepository<WadPack, WadPackName>> implements WadPackReadWriteRepo {
    public WadPackBridge(WadPackSpringRepo repo) {
        super(repo);
    }
}
