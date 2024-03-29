package lincks.maximilian.wadloader2.ddd2application.search.dto;

import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWadPath;
import lincks.maximilian.wadloader2.ddd4abstraction.PathUtil;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public record IWadDto  (
        IWadPath path,
        ImmutableTag wadTag,
        ImmutableTag defaultTag,
        Set<ImmutableTag> customTags) implements SingleWadDto {


    @Override
    public List<ImmutableTag> tags() {
        return Stream.of(List.of(defaultTag, wadTag),customTags)
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public String toString() {
        return PathUtil.getFileName(path.getPath());
    }
}
