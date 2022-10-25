package model.tags;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Tag which groups Elements of a Wad-Pack
*/
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WadPackTags")
public class WadPackTag implements Tag{

    private static final TagType tagType = TagType.WAD_PACK_TAG;
    @Column
    private String name;

    @Override
    public String tagName() {
        return name;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }
}
