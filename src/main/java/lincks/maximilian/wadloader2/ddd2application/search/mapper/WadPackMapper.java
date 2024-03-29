package lincks.maximilian.wadloader2.ddd2application.search.mapper;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.exceptions.WadPackNotFoundException;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadPackMapper {
    private final WadPackReadWriteRepo wadPackRepo;
    public static WadPackDto toDto(WadPack wadPack){
        return new WadPackDto(wadPack.getWadPackName(),
                wadPack.getIWad(),
                wadPack.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()),
                new ImmutableTag(wadPack.getWadPackTag()),
                Collections.unmodifiableMap(wadPack.getWads()));
    }

    public WadPack fromDto(WadPackDto dto){
        return wadPackRepo.findById(dto.wadPackName()).orElseThrow(WadPackNotFoundException::new);
    }
}
