package lincks.maximilian.wadloader2.ddd3domain.repos;

import java.util.List;

public interface NamedItemsRepo<T,ID> extends ReadRepo<T,ID> {
    List<T> findByNameContaining(String name);
}
