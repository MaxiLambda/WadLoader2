package lincks.maximilian.wadloader2.ddd2application.wadquerie;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IWadQuery implements SingleWadQuery<IWad> {
    private final IWadRepo iWadRepo;

    public List<IWad> getAll() {
        return iWadRepo.findAll();
    }
}
