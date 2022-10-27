package lincks.maximilian.wadloader2.model.tags;

/**
 * A Identifier for Wads or Wad-Packs
*/

public interface Tag {
     String tagName();

     TagType tagType();

     default String tagId(){
          return tagType().getPrefix()+tagName();
     }
}


