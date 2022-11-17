package lincks.maximilian.wadloader2.plugins.jpa.repository;

import lincks.maximilian.wadloader2.domain.wads.IWad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWadSpringRepo extends JpaRepository<IWad, String> {
}
