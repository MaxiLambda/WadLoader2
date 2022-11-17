package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.tags.WadTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadTagSpringRepo extends JpaRepository<WadTag, String> {
}
