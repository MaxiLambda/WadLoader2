package lincks.maximilian.wadloader2.ddd3domain.tags;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Immutable;

import java.util.Objects;


/**
 * Tag which groups Elements of a Wad-Pack
*/
@AllArgsConstructor
@Entity
@Table(name = "Wad_Pack_Tags")
@Immutable
public class WadPackTag implements Tag{

    protected WadPackTag() {

    }

    private static final TagType tagType = TagType.WAD_PACK_TAG;

    @Id
    private String name;

    @Override
    public String tagName() {
        return name;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Tag that = (Tag) o;
        return Objects.equals(tagId(), that.tagId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,tagType());
    }
}
