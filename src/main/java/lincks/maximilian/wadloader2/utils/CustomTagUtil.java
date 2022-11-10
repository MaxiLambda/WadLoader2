package lincks.maximilian.wadloader2.utils;

import lincks.maximilian.wadloader2.model.tags.CustomTag;
import lincks.maximilian.wadloader2.model.tags.TagException;
import lincks.maximilian.wadloader2.model.tags.TagType;
import lincks.maximilian.wadloader2.repos.services.CustomTagService;

//TODO maybe rename to CustomTagManager
public class CustomTagUtil {
    public static CustomTag getCustomTagForName(String name, CustomTagService customTagService){
        try{
            //fails if a CustomTag with this name already exists
            return new CustomTag(name,customTagService);
        }catch(TagException e){
            //find the existing CustomTag
            String tagId = TagType.CUSTOM_TAG.getIdForName(name);
            //if the Tag already exists, it can be found by id
            return customTagService.findById(tagId).get();
        }
    }
}
