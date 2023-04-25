package lincks.maximilian.wadloader2.ddd2application.search.query;

import lincks.maximilian.wadloader2.ddd2application.search.dto.IWadDto;
import lincks.maximilian.wadloader2.ddd2application.search.mapper.IWadMapper;
import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWadPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IWadQuery implements SingleWadQuery<IWadDto> {
    private final IWadReadWriteRepo iWadRepo;

    public void delete(IWadDto iWad){
        iWadRepo.deleteById(iWad.path());
    }

    @Override
    public Optional<IWadDto> getById(String id) {
        return iWadRepo.findById(new IWadPath(id)).map(IWadMapper::toDto);
    }

    public List<IWadDto> getAll() {
        return iWadRepo.findAll()
                .stream()
                .map(IWadMapper::toDto)
                .toList();
    }
}
