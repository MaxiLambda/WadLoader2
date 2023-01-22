package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.tags.Tag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadConfig;
import lincks.maximilian.wadloader2.ddd4abstraction.StreamUtil;

import java.util.*;

public interface WadConfigQuery<T extends WadConfig> {
    List<T> getAll();
    void delete(T item);

    default Map<ImmutableTag, List<T>> getAllGroupedByCustomTag() {
        HashMap<ImmutableTag,List<T>> groupedList = new HashMap<>();
        getAll().forEach(wad -> wad.customTags()
                .stream()
                .map(ImmutableTag::new)
                .forEach(tag -> {
                    if (groupedList.containsKey(tag))
                        groupedList.get(tag).add(wad);
                    else groupedList.put(tag, new ArrayList<>(List.of(wad)));}));
        return groupedList;
    }

    default List<T> getByTag(Tag tag){
        return getAll().stream()
                .filter(StreamUtil.filter(
                        T::tags,
                        tags -> tags.contains(tag)))
                .toList();
    }

}
