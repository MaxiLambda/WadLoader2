package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.domain.wads.Wad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadRepo extends JpaRepository<Wad, String> {
}
