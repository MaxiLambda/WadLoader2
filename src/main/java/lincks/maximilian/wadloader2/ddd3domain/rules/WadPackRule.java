package lincks.maximilian.wadloader2.ddd3domain.rules;

import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;

import java.util.function.Predicate;

public interface WadPackRule {
    Predicate<WadPack> getPredicate(WadReadWriteRepo wadRepo);
}
