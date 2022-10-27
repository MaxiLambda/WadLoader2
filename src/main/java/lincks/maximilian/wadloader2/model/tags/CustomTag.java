package lincks.maximilian.wadloader2.model.tags;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
