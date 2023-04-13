package lincks.maximilian.wadloader2.ddd3domain.wads;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@RequiredArgsConstructor
@Getter
public class WadPath implements Serializable {

    @Column(name = "Wad_Path")
    private final String path;

    protected WadPath() {
        path = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WadPath wadPath = (WadPath) o;

        return Objects.equals(path, wadPath.path);
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }
}
