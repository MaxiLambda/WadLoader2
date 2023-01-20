package lincks.maximilian.wadloader2.ddd3domain.wads;

import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;

import java.util.List;

public interface SingleWad extends WadConfig {
    List<SingleWad> allWads();

    DefaultTag getDefaultTag();
}
