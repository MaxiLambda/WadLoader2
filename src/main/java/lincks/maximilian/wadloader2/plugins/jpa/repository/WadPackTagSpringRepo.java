package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.tags.WadPackTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadPackTagSpringRepo extends JpaRepository<WadPackTag, String> {
}
