package lincks.maximilian.wadloader2.ddd1adapter;

import lincks.maximilian.wadloader2.ddd1adapter.dto.WadDto;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;

import java.util.stream.Collectors;

public class WadMapper {
    private WadMapper(){}
    public static WadDto toDto(Wad wad){
        return new WadDto(wad.getPath(),
                new ImmutableTag(wad.getWadTag()),
                new ImmutableTag(wad.getDefaultTag()),
                wad.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()));
    }
}
