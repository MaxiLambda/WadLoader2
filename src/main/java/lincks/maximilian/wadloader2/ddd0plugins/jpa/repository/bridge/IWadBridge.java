package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.IWadSpringRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWadPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IWadBridge extends AbstractBridge<IWad, IWadPath, JpaRepository<IWad, IWadPath>> implements IWadReadWriteRepo {

    public IWadBridge(IWadSpringRepo repo) {
        super(repo);
    }

}
