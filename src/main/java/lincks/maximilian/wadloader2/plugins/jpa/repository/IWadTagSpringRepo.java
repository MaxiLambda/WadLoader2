package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.tags.IWadTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWadTagSpringRepo extends JpaRepository<IWadTag, String> {
}

