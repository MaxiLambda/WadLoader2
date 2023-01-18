package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository;

import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWadSpringRepo extends JpaRepository<IWad, String> {
}
