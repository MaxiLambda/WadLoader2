package lincks.maximilian.wadloader2.ddd2application.tags;

import lincks.maximilian.wadloader2.ddd2application.wadpack.InvalidWadPackConfigurationException;
import lincks.maximilian.wadloader2.ddd2application.wadpack.WadPackFactory;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadPackReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.tags.CustomTag;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisabledInNativeImage
class CustomTagMarkerTest {
    @InjectMocks
    CustomTagMarker customTagMarker;
    @Mock
    WadPackFactory wadPackFactory;
    @Mock
    WadReadWriteRepo wadRepo;
    @Mock
    WadPackReadWriteRepo wadPackRepo;
    WadPack wadPack;
    String tagName = "Cat Coach";
    String otherTagName = "Ne, gerade nicht!";

    @BeforeEach
    void setUp() {
        when(wadPackRepo.findAll()).thenReturn(List.of());

        wadPack = TestUtil.getWadPack(wadPackRepo);
    }

    @Test
    void addCustomTagFails() throws InvalidWadPackConfigurationException {
        doThrow(InvalidWadPackConfigurationException.class).when(wadPackFactory).persistWadPack(any());

        wadPack.addCustomTag(tagName);

        assertThrows(InvalidWadPackConfigurationException.class,() -> customTagMarker.addCustomTag(wadPack,otherTagName));

        assert wadPack.getCustomTags().contains(new CustomTag(tagName));
        assertFalse(wadPack.getCustomTags().contains(new CustomTag(otherTagName)));
    }
}