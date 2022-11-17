package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.domain.wads.IWad;
import lincks.maximilian.wadloader2.repos.IWadRepo;
import org.springframework.stereotype.Service;

@Service
public class IWadService extends AbstractService<IWad, String>{

    public IWadService(IWadRepo repo) {
        super(repo);
    }
}
