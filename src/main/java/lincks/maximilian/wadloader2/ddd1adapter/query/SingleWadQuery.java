package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.SingleWad;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface SingleWadQuery<T extends SingleWad> extends WadConfigQuery<T>{
    /**
     * Returns all Wads grouped by their parent Folders
     */
    default Map<ImmutableTag, List<T>> getAllWadsGroupedByFolder() {
        return getAll().stream().collect(Collectors.groupingBy(wad -> new ImmutableTag(wad.getDefaultTag())));
    }

    default Optional<T> getById(String id){
        return getAll().stream()
                .filter(StreamUtil.filter(
                        T::allWadIds,
                        ids -> ids.contains(id)))
                .findFirst();
    }

    default List<T> getById(List<String> ids){
        return getAll().stream()
                //Checks which wads have Ids corresponding to the searched ones
                .filter(StreamUtil.filter(
                        //always returns a list with 1 element, because T is instance of SingleWad
                        T::allWadIds,
                        allWadIds -> allWadIds.stream().anyMatch(ids::contains)))
                .toList();
    }
}
