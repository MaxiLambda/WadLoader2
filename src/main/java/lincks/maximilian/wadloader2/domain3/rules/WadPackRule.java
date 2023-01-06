package lincks.maximilian.wadloader2.domain3.rules;

import lincks.maximilian.wadloader2.domain3.repos.WadRepo;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;

import java.util.function.Predicate;

public interface WadPackRule {
    public Predicate<WadPack> getPredicate(WadRepo wadRepo);
}
