package lincks.maximilian.wadloader2.ddd1adapter;

import lincks.maximilian.wadloader2.ddd1adapter.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.Collections;
import java.util.stream.Collectors;

public class WadPackMapper {
    private WadPackMapper(){}
    public static WadPackDto toDto(WadPack wadPack){
        return new WadPackDto(wadPack.getName(),
                wadPack.getIWad(),
                wadPack.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()),
                new ImmutableTag(wadPack.getWadPackTag()),
                Collections.unmodifiableMap(wadPack.getWads()));
    }
}
