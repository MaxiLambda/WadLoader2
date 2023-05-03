package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WadPackSpringRepo extends JpaRepository<WadPack, WadPackName> {
}
