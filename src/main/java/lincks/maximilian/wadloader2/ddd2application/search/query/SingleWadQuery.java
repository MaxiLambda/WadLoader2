package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.SingleWadDto;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface SingleWadQuery<T extends SingleWadDto> extends WadConfigQuery<T>{
    /**
     * Returns all Wads grouped by their parent Folders
     */
    default Map<ImmutableTag, List<T>> getAllWadsGroupedByFolder() {
        return getAll().stream().collect(Collectors.groupingBy(wad -> new ImmutableTag(wad.getDefaultTag())));
    }

    Optional<T> getById(String id);
    List<T> getByDefaultTag(String path);
    default List<T> getById(List<String> ids){
        return ids.stream().map(this::getById).filter(Optional::isPresent).map(Optional::get).toList();
    }
}
