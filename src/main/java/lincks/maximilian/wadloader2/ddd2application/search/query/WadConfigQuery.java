package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadConfigDto;

import java.util.List;

public interface WadConfigQuery<T extends WadConfigDto> {
    List<T> getAll();
    void delete(T item);
}
