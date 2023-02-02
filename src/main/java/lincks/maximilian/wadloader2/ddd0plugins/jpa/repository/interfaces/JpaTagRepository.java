package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface JpaTagRepository<T, ID> extends JpaRepository<T,ID> {
    List<T> findByNameContaining(ID id);
}
