package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.SingleWadDto;

import java.util.Optional;

public interface SingleWadQuery<T extends SingleWadDto> extends WadConfigQuery<T>{

    Optional<T> getById(String id);
}
