package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.model.tags.DefaultTag;
import lincks.maximilian.wadloader2.repos.DefaultTagRepo;
import org.springframework.stereotype.Service;

@Service
public class DefaultTagService extends AbstractService<DefaultTag, String>{

    public DefaultTagService(DefaultTagRepo repo) {
        super(repo);
    }
}
