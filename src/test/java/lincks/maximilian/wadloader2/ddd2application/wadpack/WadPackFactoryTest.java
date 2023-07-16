package lincks.maximilian.wadloader2.ddd2application.wadpack;

import lincks.maximilian.wadloader2.ddd3domain.repos.*;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMaxTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.ContainsMinTagRule;
import lincks.maximilian.wadloader2.ddd3domain.rules.ExclusiveTagRule;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.model.wads.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
class WadPackFactoryTest {
    @Mock
    ContainsMinTagRuleReadWriteRepo minTagRuleRepo;
    @Mock
    ContainsMaxTagRuleReadWriteRepo maxTagRuleRepo;
    @Mock
    ExclusiveTagRuleReadWriteRepo exclusiveTagRuleRepo;

    @Mock
    WadPackReadWriteRepo wadPackRepo;
    @Mock
    WadReadWriteRepo wadRepo;

    @InjectMocks
    WadPackFactory wadPackFactory;

    WadPack wadPack;

    Wad wad1;
    Wad wad2;

    String customTagName = "Jamie's Tag";
    String otherCustomTagName = "You turn my Software into Hardware";
    ContainsMinTagRule minTagRule = new ContainsMinTagRule(2,new CustomTag(customTagName), new CustomTag(customTagName));
    ContainsMaxTagRule maxTagRule = new ContainsMaxTagRule(1,new CustomTag(customTagName), new CustomTag(customTagName));


    @BeforeEach
     void beforeAll() {
        when(wadPackRepo.findAll()).thenReturn(List.of());

        wadPack = TestUtil.getWadPack(wadPackRepo);

        var wads = TestUtil.wadPaths.stream().map(Wad::new).toList();
        wad1 = wads.get(0);
        wad2 = wads.get(1);
        wadPack.addWad(wad1);
        wadPack.addWad(wad2);

        when(wadRepo.findById(wad1.getPath())).thenReturn(Optional.of(wad1));
        when(wadRepo.findById(wad2.getPath())).thenReturn(Optional.of(wad2));
     }

    @Test
    void persistWadPackMinTagSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of(minTagRule));
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);
        wad2.addCustomTag(customTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));

    }

    @Test
    void persistWadPackMinTagNotSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of(minTagRule));
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackMaxTagSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of(maxTagRule));
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackMaxTagNotSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of(maxTagRule));
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of());

        wad1.addCustomTag(customTagName);
        wad2.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));
    }



    @Test
    void persistWadPackExclusiveTagRuleSatisfied() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of(new ExclusiveTagRule(
                List.of(new CustomTag(customTagName)),
                List.of(new CustomTag(otherCustomTagName))
        )));

        wad1.addCustomTag(otherCustomTagName);

        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void wadInEachSetExclusiveRuleTest(){
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of(new ExclusiveTagRule(
                List.of(new CustomTag(customTagName)),
                List.of(new CustomTag(customTagName))
        )));

        wad1.addCustomTag(customTagName);
        assertDoesNotThrow(() -> wadPackFactory.persistWadPack(wadPack));
    }

    @Test
    void persistWadPackExclusiveTagRuleSatisfiedEmpty() {
        when(minTagRuleRepo.findAll()).thenReturn(List.of());
        when(maxTagRuleRepo.findAll()).thenReturn(List.of());
        when(exclusiveTagRuleRepo.findAll()).thenReturn(List.of(new ExclusiveTagRule(
                List.of(),
                List.of()
        )));

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
                List.of(new CustomTag(customTagName),new CustomTag("random")),
                List.of(new CustomTag(otherCustomTagName), new CustomTag("You did not expect this"))
        )));

        wad1.addCustomTag(otherCustomTagName);
        wad2.addCustomTag(customTagName);

        assertThrows( InvalidWadPackConfigurationException.class,() -> wadPackFactory.persistWadPack(wadPack));

    }
}