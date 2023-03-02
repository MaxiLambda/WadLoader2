package lincks.maximilian.wadloader2.ddd1adapter.mapper;

import lincks.maximilian.wadloader2.ddd1adapter.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd1adapter.dto.WadConfigDto;
import lincks.maximilian.wadloader2.ddd1adapter.dto.WadDto;
import lincks.maximilian.wadloader2.ddd1adapter.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadConfig;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WadConfigMapper {
    private final IWadMapper iWadMapper;
    private final WadPackMapper wadPackMapper;
    private final WadMapper wadMapper;

    public static WadConfigDto toDto(WadConfig wadConfig){
        return switch (wadConfig){
            case IWad iwad -> IWadMapper.toDto(iwad);
            case WadPack wadPack -> WadPackMapper.toDto(wadPack);
            case Wad wad -> WadMapper.toDto(wad);
        };
    }

    public WadConfig fromDto(WadConfigDto dto){
        return switch (dto){
            case IWadDto iwadDto -> iWadMapper.fromDto(iwadDto);
            case WadPackDto wadPackDto -> wadPackMapper.fromDto(wadPackDto);
            case WadDto wadDto -> wadMapper.fromDto(wadDto);
        };
    }
}
