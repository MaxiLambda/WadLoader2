package lincks.maximilian.wadloader2.ddd3domain.wads;

import lincks.maximilian.wadloader2.ddd3domain.tags.DefaultTag;

public sealed interface SingleWad extends WadConfig permits IWad, Wad {

    DefaultTag getDefaultTag();
}
