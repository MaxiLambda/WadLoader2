package lincks.maximilian.wadloader2.domain3.tags;

/**
 * A Identifier for Wads or Wad-Packs
 * basically a DDD Value Object for the TagName
*/

public interface Tag {
     String tagName();

     TagType tagType();

     default String tagId(){
          return tagType().getIdForName(tagName());
     }
}


