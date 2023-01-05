package lincks.maximilian.wadloader2.domain3.tags;

import java.io.Serializable;

public class TagId implements Serializable {
    private String name;
    private TagType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagId tagId = (TagId) o;

        if (!name.equals(tagId.name)) return false;
        return type == tagId.type;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
