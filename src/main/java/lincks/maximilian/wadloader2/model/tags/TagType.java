package lincks.maximilian.wadloader2.model.tags;

import lombok.Getter;

@Getter
public enum TagType {
    //All prefixes should be unique, because they are used to generate ids
    WAD_PACK_TAG("p"),
    WAD_TAG("w"),
    DEFAULT_TAG("d"),
    CUSTOM_TAG("c");

    private final String prefix;

    TagType(String prefix) {
        this.prefix = prefix+":";
    }

    public String getIdForName(String name){
        return getPrefix()+name;
    }

}
