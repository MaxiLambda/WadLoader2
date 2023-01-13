package lincks.maximilian.wadloader2.application2.wadpack;

import lincks.maximilian.wadloader2.domain3.repos.*;
import lincks.maximilian.wadloader2.domain3.rules.ContainsMaxTagRule;
import lincks.maximilian.wadloader2.domain3.rules.ContainsMinTagRule;
import lincks.maximilian.wadloader2.domain3.rules.ExclusiveTagRule;
import lincks.maximilian.wadloader2.domain3.rules.ExclusiveWadRule;
import lincks.maximilian.wadloader2.domain3.tags.CustomTag;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import lincks.maximilian.wadloader2.domain3.wads.WadPack;
import lincks.maximilian.wadloader2.model.wads.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WadPackFactoryTest {
    @Mock
    ContainsMinTagRuleRepo minTagRuleRepo;
    @Mock
    ContainsMaxTagRuleRepo maxTagRuleRepo;
    @Mock
    ExclusiveTagRuleRepo exclusiveTagRuleRepo;
    @Mock
    ExclusiveWadRuleRepo exclusiveWadRuleRepo;

    @Mock
    WadPackRepo wadPackRepo;
    @Mock
    WadRepo wadRepo;

    @InjectMocks
    WadPackFactory wadPackFactory;

    WadPack wadPack;

    Wad wad1;
    Wad wad2;

    String customTagName = "Jamie's Tag";
    String otherCustomTagName = "You turn my Software into Hardware";
    ContainsMinTagRule minTagRule = new ContainsMinTagRule(2,new CustomTag(customTagName));
    ContainsMaxTagRule maxTagRule = new ContainsMaxTagRule(1,new CustomTag(customTagName));


    @BeforeEach
     void beforeAll() {
        when(wadPackRepo.findAll()).thenReturn(List.of());

        wadPack = TestUtil.getWadPack(wadPackRepo);

        var wads = TestUtil.wadPaths.stream().map(Wad::new).toList();
        wad1 = wads.get(0);
        wad2 = wads.get(1);
        wadPack.addWad(wad1);
        wadPack.addWad(wad2);

        when(wadRepo.findById(Mockito.contains(wad1.getPath()))).thenReturn(Optional.of(wad1));
        when(wadRepo.findById(Mockito.contains(wad2.getPath()))).thenReturn(Optional.of(wad2));
     }

    @Test
    void persistWadPackMinTagSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of(minTagRule));
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);
        wad2.addCustomTag(customTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));

    }

    @Test
    void persistWadPackMinTagNotSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of(minTagRule));
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackMaxTagSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of(maxTagRule));
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackMaxTagNotSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of(maxTagRule));
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);
        wad2.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackExclusiveWadRuleSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of(new ExclusiveWadRule(wad1.getPath(), Set.of(new CustomTag(customTagName)))));

        wad1.addCustomTag(otherCustomTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackExclusiveWadRuleNotSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of(new ExclusiveWadRule(wad1.getPath(), Set.of(new CustomTag(customTagName)))));

        wad1.addCustomTag(otherCustomTagName);
        wad2.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackExclusiveTagRuleSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of(new ExclusiveTagRule(
                Set.of(new CustomTag(customTagName)),
                Set.of(new CustomTag(otherCustomTagName))
        )));
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(otherCustomTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackExclusiveTagRuleSatisfiedEmpty() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of(new ExclusiveTagRule(
                Set.of(),
                Set.of()
        )));
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(otherCustomTagName);
        wad2.addCustomTag(otherCustomTagName);
        wad2.addCustomTag(customTagName);


        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackExclusiveTagRuleNotSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of(new ExclusiveTagRule(
                Set.of(new CustomTag(customTagName),new CustomTag("random")),
                Set.of(new CustomTag(otherCustomTagName), new CustomTag("You did not expect this"))
        )));
        when(exclusiveWadRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(otherCustomTagName);
        wad2.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));

    }
}