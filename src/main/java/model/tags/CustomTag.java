package model.tags;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


/**
 * User Created Tag
*/
@AllArgsConstructor
@Entity
@Table(name = "CustomTags")
public class CustomTag implements Tag{

    protected CustomTag(){}

    private static final TagType tagType = TagType.CUSTOM_TAG;
    @Column
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
}
