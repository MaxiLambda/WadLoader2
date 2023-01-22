package lincks.maximilian.wadloader2.ddd1adapter.query;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IWadQuery implements SingleWadQuery<IWad> {
    private final IWadRepo iWadRepo;

    public void delete(IWad iWad){
        delete(iWad);
    }

    public List<IWad> getAll() {
        return iWadRepo.findAll();
    }
}
