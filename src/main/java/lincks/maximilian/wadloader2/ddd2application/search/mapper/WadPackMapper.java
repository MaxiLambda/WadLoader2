package lincks.maximilian.wadloader2.ddd2application.search.mapper;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.exceptions.IWadNotFoundException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackFactory;
import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.ImmutableTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPackName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WadPackMapper {
    private final WadPackReadWriteRepo wadPackRepo;
    private final WadPackFactory factory;
    private final IWadReadWriteRepo iWadRepo;

    public static WadPackDto toDto(WadPack wadPack){
        return new WadPackDto(wadPack.getName().getName(),
                wadPack.getIWad(),
                wadPack.getCustomTags().stream().map(ImmutableTag::new).collect(Collectors.toUnmodifiableSet()),
                new ImmutableTag(wadPack.getWadPackTag()),
                Collections.unmodifiableMap(wadPack.getWads()));
    }

    public WadPack fromDto(WadPackDto dto){
        var packOptional = wadPackRepo.findByName(new WadPackName(dto.name()));
        if(packOptional.isPresent()) return packOptional.get();
        IWad iwad = iWadRepo.findById(dto.iWad()).orElseThrow(IWadNotFoundException::new);

       var pack = new WadPack(dto.name(),iwad);
       pack.setWads(dto.wads());
       return pack;
    }
}
