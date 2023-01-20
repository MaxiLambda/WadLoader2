package lincks.maximilian.wadloader2.ddd3domain.rules;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.function.Predicate;

public interface WadPackRule {
    public Predicate<WadPack> getPredicate(WadRepo wadRepo);
}
