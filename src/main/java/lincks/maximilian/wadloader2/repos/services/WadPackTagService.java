package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.domain.tags.WadPackTag;
import lincks.maximilian.wadloader2.repos.WadPackTagRepo;
import org.springframework.stereotype.Service;

@Service
public class WadPackTagService extends AbstractService<WadPackTag, String>{
    public WadPackTagService(WadPackTagRepo repo) {
        super(repo);
    }
}
