package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.domain.tags.WadTag;
import lincks.maximilian.wadloader2.repos.WadTagRepo;
import org.springframework.stereotype.Service;

@Service
public class WadTagService extends AbstractService<WadTag, String>{
    public WadTagService(WadTagRepo repo) {
        super(repo);
    }
}
