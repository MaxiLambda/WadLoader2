package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTagSpringRepo extends JpaRepository<CustomTag, String> {
}
