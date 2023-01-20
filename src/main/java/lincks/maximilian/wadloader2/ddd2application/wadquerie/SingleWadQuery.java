package lincks.maximilian.wadloader2.ddd2application.wadquerie;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.SingleWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface SingleWadQuery<T extends SingleWad> extends WadConfigQuery<T>{
    /**
     * Returns all Wads grouped by their parent Folders
     */
    default Map<ImmutableTag, List<T>> getAllWadsGroupedByFolder() {
        return getAll().stream().collect(Collectors.groupingBy(wad -> new ImmutableTag(wad.getDefaultTag())));
    }
}
