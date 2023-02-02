package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.interfaces.JpaTagRepository;
import lincks.maximilian.wadloader2.ddd3domain.repos.CustomTagReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomTagBridge extends AbstractBridge<CustomTag, String, JpaTagRepository<CustomTag, String>> implements CustomTagReadWriteRepo {

    public CustomTagBridge(JpaTagRepository<CustomTag,String> repo) {
        super(repo);
    }

    @Override
    public List<CustomTag> findByNameContaining(String name) {
        return repo.findByNameContaining(name);
    }
}
