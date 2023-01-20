package lincks.maximilian.wadloader2.ddd3domain.wads;

import java.util.List;

public interface SingleWad extends WadConfig {
    List<? extends SingleWad> allWads();
}
