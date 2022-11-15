package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.model.tags.IWadTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWadTagRepo extends JpaRepository<IWadTag, String> {
}

