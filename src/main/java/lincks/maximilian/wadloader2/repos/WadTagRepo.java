package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.domain.tags.WadTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadTagRepo extends JpaRepository<WadTag, String> {
}
