package lincks.maximilian.wadloader2.plugins0.jpa.repository;

import lincks.maximilian.wadloader2.domain3.wads.IWad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWadSpringRepo extends JpaRepository<IWad, String> {
}
