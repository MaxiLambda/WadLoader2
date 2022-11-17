package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.domain.tags.DefaultTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultTagRepo extends JpaRepository<DefaultTag, String> {
}
