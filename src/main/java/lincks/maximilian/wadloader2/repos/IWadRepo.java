package lincks.maximilian.wadloader2.repos;

import lincks.maximilian.wadloader2.model.wads.IWad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWadRepo extends JpaRepository<IWad, String> {
}
