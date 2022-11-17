package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.domain.wads.WadPack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadPackRepo extends JpaRepository<WadPack, String> {
}
