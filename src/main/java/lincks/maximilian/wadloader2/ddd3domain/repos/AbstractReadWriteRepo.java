package lincks.maximilian.wadloader2.ddd3domain.repos;

import java.util.List;
import java.util.Optional;

public interface AbstractReadWriteRepo<T,ID> extends ReadRepo<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    void delete(T toDelete);

    void deleteById(ID id);

    void deleteAll();

    T save(T toAdd);

    boolean exists(ID id);
}
