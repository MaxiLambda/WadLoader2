package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.tags.CustomTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTagSpringRepo extends JpaRepository<CustomTag, String> {
}
