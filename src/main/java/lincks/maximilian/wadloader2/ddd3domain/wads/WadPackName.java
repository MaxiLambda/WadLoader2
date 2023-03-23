package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
@Getter
public class WadPackName implements Serializable {
    private final String name;
    protected WadPackName(){
        this.name = null;
    }
}
