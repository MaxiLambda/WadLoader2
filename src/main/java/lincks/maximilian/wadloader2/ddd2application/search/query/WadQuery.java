package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.WadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.WadMapper;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WadQuery implements SingleWadQuery<WadDto> {

    private final WadReadWriteRepo wadRepo;

    public List<WadDto> getAll() {
        return wadRepo.findAll()
                .stream()
                .map(WadMapper::toDto)
                .toList();
    }

    @Override
    public Optional<WadDto> getById(String id) {
        return wadRepo.findById(new WadPath(id))
                .map(WadMapper::toDto);
    }

    public void delete(WadDto wad){
        wadRepo.deleteById(wad.path());
    }

}
