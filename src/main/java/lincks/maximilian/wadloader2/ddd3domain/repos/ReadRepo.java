package lincks.maximilian.wadloader2.ddd3domain.repos;

import java.util.List;
import java.util.Optional;

public interface ReadRepo<T,ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
}
