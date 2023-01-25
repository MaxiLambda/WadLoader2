package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IWadQuery implements SingleWadQuery<IWad> {
    private final IWadRepo iWadRepo;

    public void delete(IWad iWad){
        delete(iWad);
    }

    @Override
    public Optional<IWad> getById(String id) {
        return iWadRepo.findById(id);
    }

    public List<IWad> getAll() {
        return iWadRepo.findAll();
    }
}
