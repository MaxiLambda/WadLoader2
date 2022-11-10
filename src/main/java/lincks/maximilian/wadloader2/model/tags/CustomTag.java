package lincks.maximilian.wadloader2.model.tags;

import lincks.maximilian.wadloader2.repos.services.CustomTagService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User Created Tag
*/
@Entity
@Table(name = "Custom_Tags")
public class CustomTag implements Tag{

    protected CustomTag(){}

    public CustomTag(String name, CustomTagService customTagService) throws TagException{
        if(customTagService.exists(TagType.CUSTOM_TAG.getIdForName(name)))
            throw new TagException("CustomTag name %s already exists".formatted(name));
        this.name = name;
    }

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
