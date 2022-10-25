package model.tags;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * User Created Tag
*/
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CustomTags")
public class CustomTag implements Tag{

    private static final TagType tagType = TagType.CUSTOM_TAG;
    @Column
    private String tag;

    @Override
    public String tagName() {
        return tag;
    }

    @Override
    public TagType tagType() {
        return tagType;
    }
}
