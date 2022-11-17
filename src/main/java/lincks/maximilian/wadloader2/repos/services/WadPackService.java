package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.domain.wads.WadPack;
import lincks.maximilian.wadloader2.repos.WadPackRepo;
import org.springframework.stereotype.Service;

@Service
public class WadPackService extends AbstractService<WadPack, String>{
    public WadPackService(WadPackRepo repo) {
        super(repo);
    }
}
