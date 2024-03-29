package lincks.maximilian.wadloader2.ddd0plugins.jpa.repository.bridge;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class  AbstractBridge<T,ID,R extends JpaRepository<T,ID>> {
    protected final R repo;

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

    public boolean exists(ID id){
        return repo.existsById(id);
    }
}
