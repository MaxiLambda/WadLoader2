package lincks.maximilian.wadloader2.ddd3domain.wads;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
@Getter
public class WadLoadOrderId implements Serializable {

    private final WadPackName wadPackName;
    private final int loadOrder;

    protected WadLoadOrderId() {
        wadPackName = null;
        loadOrder = -1;
    }
}
