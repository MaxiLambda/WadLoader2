package lincks.maximilian.wadloader2.domain3.wads;

import java.util.List;

public interface SingleWad extends WadConfig {
    List<? extends SingleWad> allWads();
}
