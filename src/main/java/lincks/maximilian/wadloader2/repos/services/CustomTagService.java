package lincks.maximilian.wadloader2.repos.services;

import lincks.maximilian.wadloader2.domain.tags.CustomTag;
import lincks.maximilian.wadloader2.repos.CustomTagRepo;
import org.springframework.stereotype.Service;

@Service
public class CustomTagService extends AbstractService<CustomTag, String>{

    public CustomTagService(CustomTagRepo repo) {
        super(repo);
    }
}
