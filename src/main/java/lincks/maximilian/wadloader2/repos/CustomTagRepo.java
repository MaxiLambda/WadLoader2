package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.domain.tags.CustomTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTagRepo extends JpaRepository<CustomTag, String> {
}
