package lincks.maximilian.wadloader2.application2.wadpack.rules;

import lincks.maximilian.wadloader2.domain3.repos.ContainsMaxTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.repos.ContainsMinTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.repos.ExclusiveTagRuleRepo;
import lincks.maximilian.wadloader2.domain3.repos.ExclusiveWadRuleRepo;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleFactory {

    @Delegate(excludes = ExcludeFunctionality.class)
    private final ExclusiveTagRuleRepo exclusiveTagRuleRepo;
    @Delegate(excludes = ExcludeFunctionality.class)
    private final ExclusiveWadRuleRepo exclusiveWadRuleRepo;
    @Delegate(excludes = ExcludeFunctionality.class)
    private final ContainsMinTagRuleRepo minTagRuleRepo;
    @Delegate(excludes = ExcludeFunctionality.class)
    private final ContainsMaxTagRuleRepo maxTagRuleRepo;

    @SuppressWarnings("unused")
    private interface ExcludeFunctionality {
        void deleteById(Integer id);
        boolean exists(Integer id);
        Optional<Object> findById(Integer id);
        void deleteAll();
        void findAll();
    }
}
