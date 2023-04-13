package lincks.maximilian.wadloader2.ddd2application.search.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWadPath;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public record WadPackDto(WadPackName wadPackName,
                         IWadPath iWad,
                         Set<ImmutableTag> customTags,
                         ImmutableTag wadPackTag,
                         Map<Integer, WadPath> wads
                         ) implements WadConfigDto {
    @Override
    public List<String> allWadIds() {
        return wads.values().stream().map(WadPath::getPath).toList();
    }

    @Override
    public List<ImmutableTag> tags() {
        return Stream.of(List.of(wadPackTag),customTags)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public String toString() {
        return wadPackName.name;
    }
}
