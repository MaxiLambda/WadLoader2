package lincks.maximilian.wadloader2.ddd3domain.repos.readonly;

import java.util.List;
import java.util.Optional;

public interface ReadOnlyRepo<T,ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    List<T> findByNameContaining(String name);
}
