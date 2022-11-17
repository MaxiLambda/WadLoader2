package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.domain.tags.IWadTag;
import lincks.maximilian.wadloader2.repos.IWadTagRepo;
import org.springframework.stereotype.Service;

@Service
public class IWadTagService extends AbstractService<IWadTag,String>{
    public IWadTagService(IWadTagRepo repo) {
        super(repo);
    }
}
