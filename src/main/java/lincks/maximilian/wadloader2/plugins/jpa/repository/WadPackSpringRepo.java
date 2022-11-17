package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.wads.WadPack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadPackSpringRepo extends JpaRepository<WadPack, String> {
}
