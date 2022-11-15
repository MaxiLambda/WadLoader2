package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.model.wads.IWad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class IWadService extends AbstractService<IWad, String>{

    public IWadService(JpaRepository<IWad, String> repo) {
        super(repo);
    }
}
