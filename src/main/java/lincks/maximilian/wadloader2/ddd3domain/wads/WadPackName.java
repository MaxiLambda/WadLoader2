package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class WadPackName implements Serializable {

    @Column(name = "Wad_Pack_Name", nullable = false)
    public final String name;

    protected WadPackName() {
        name = null;
    }

    public WadPackName(String name){
        this.name = name;
    }
}
