package lincks.maximilian.wadloader2.ddd2application.search.mapper;

import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadConfigDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadDto;
import lincks.maximilian.wadloader2.ddd2application.search.dto.WadPackDto;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WadConfigMapper {
    private final IWadMapper iWadMapper;
    private final WadPackMapper wadPackMapper;
    private final WadMapper wadMapper;

    public WadConfig fromDto(WadConfigDto dto){
        return switch (dto){
            case IWadDto iwadDto -> iWadMapper.fromDto(iwadDto);
            case WadPackDto wadPackDto -> wadPackMapper.fromDto(wadPackDto);
            case WadDto wadDto -> wadMapper.fromDto(wadDto);
        };
    }
}
