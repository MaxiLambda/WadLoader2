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
public class IWadPath implements Serializable {

    @Column(name = "IWad_Path")
    private final String path;

    protected IWadPath() {
        path = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IWadPath iWadPath = (IWadPath) o;

        return Objects.equals(path, iWadPath.path);
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }
}
