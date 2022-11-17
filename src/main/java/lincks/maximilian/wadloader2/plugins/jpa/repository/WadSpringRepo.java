package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.wads.Wad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadSpringRepo extends JpaRepository<Wad, String> {
}
