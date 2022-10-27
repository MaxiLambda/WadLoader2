package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.wads.Wad;
import lincks.maximilian.wadloader2.repos.WadRepo;
import org.springframework.stereotype.Service;

@Service
public class WadService extends AbstractService<Wad, String>{

    public WadService(WadRepo repo) {
        super(repo);
    }
}
