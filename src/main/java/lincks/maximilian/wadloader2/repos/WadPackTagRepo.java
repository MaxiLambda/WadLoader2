package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.domain.tags.WadPackTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadPackTagRepo extends JpaRepository<WadPackTag, String> {
}
