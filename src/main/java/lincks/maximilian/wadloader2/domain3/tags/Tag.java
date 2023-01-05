package lincks.maximilian.wadloader2.domain3.tags;

import lincks.maximilian.wadloader2.abstraction4.PathUtil;
import lombok.Getter;

import javax.persistence.*;
import java.nio.file.Path;

/**
 * A Identifier for Wads or Wad-Packs
 * basically a DDD Value Object for the TagName
*/

@Entity
@Table(name = "tag")
@Getter
@IdClass(TagId.class)
public class Tag {

     protected Tag(){}

     private Tag(TagType type, String name){
          this.type = type;
          this.name = name;
     }

     @Id
     private String name;

     @Id
     @Enumerated(EnumType.STRING)
     private TagType type;


     public String tagId(){
          return type.getIdForName(name);
     }

     public static Tag customTag(String name){
          return new Tag(TagType.CUSTOM_TAG,name);
     }

     public static Tag defaultTag(Path wadPath){
          return new Tag(TagType.DEFAULT_TAG, wadPath.toAbsolutePath().getParent().toString());
     }

     public static Tag iWadTag(Path iWadPath){
          return new Tag(TagType.I_WAD_TAG, PathUtil.fileNameWithoutExtension(iWadPath));
     }

     public static Tag wadPackTag(String name){
          return new Tag(TagType.WAD_PACK_TAG,name);
     }

     public static Tag wadTag(Path wadPath){
          return new Tag(TagType.WAD_TAG, PathUtil.fileNameWithoutExtension(wadPath));
     }
}


