package lincks.maximilian.wadloader2.plugins0.jpa.repository.bridge;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractBridge<T,ID> {
    protected final JpaRepository<T,ID> repo;

    public List<T> findAll(){
        return repo.findAll();
    }
    public Optional<T> findById(ID id){
        return repo.findById(id);
    }

    public void delete(T toDelete){
        repo.delete(toDelete);
    }

    public void deleteById(ID id){
        repo.deleteById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public T save(T toAdd){
        return repo.save(toAdd);
    }

    public List<T> saveAll(Iterable<T> toSave){
        return repo.saveAll(toSave);
    }

    public boolean exists(ID id){
        return repo.existsById(id);
    }
}
