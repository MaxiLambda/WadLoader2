package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge.readonly;

import lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.interfaces.JpaTagRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractReadOnlyTagBridge<T, ID>{

    protected final JpaTagRepository<T,ID> repo;

    public List<T> findAll(){
        return repo.findAll();
    }
    public Optional<T> findById(ID id){
        return repo.findById(id);
    }
}
