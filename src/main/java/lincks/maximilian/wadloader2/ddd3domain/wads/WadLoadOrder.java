package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Entity
@Embeddable
public class WadLoadOrder implements Serializable {

    @EmbeddedId
    private final WadLoadOrderId id;

    private final WadPath wadPath;

    protected WadLoadOrder(){
        id = null;
        wadPath = null;
    }
}
