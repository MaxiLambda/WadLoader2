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
        if(dto instanceof IWadDto iwadDto)
            return iWadMapper.fromDto(iwadDto);
        else if(dto instanceof WadPackDto wadPackDto)
            return wadPackMapper.fromDto(wadPackDto);
        else if (dto instanceof  WadDto wadDto)
            return wadMapper.fromDto(wadDto);
        throw new IllegalStateException();
    }
}
