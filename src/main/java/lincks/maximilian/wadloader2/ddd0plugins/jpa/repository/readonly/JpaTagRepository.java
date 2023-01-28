package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.readonly;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTagRepository<T, ID> extends JpaRepository<T,ID> {
    List<T> findByNameContaining(ID id);
}
