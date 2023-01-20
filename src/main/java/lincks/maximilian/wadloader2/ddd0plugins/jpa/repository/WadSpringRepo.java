package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadSpringRepo extends JpaRepository<Wad, String> {
}
