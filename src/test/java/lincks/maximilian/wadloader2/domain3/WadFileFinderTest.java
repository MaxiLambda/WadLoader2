package lincks.maximilian.wadloader2.domain3;

import lincks.maximilian.wadloader2.abstraction4.config.WadExtensionConfiguration;
import lincks.maximilian.wadloader2.domain3.wads.Wad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WadFileFinderTest {

    @Mock
    WadExtensionConfiguration wadExtCfg;

    @InjectMocks
    WadFileFinder wadFileFinder;

    final Path testResourcesPath = Path.of("src/test/resources/testDir");
    final List<Wad> wads = Stream.of(
            "src/test/resources/testDir/wad1.pk3",
            "src/test/resources/testDir/subDir/Wad2.pk3",
            "src/test/resources/testDir/subDir/Wad3.pk3"
        )
            .map(Path::of)
            .map(Wad::new)
            .toList();

    @Test
    void findWads() {

        when(wadExtCfg.getAllowed()).thenReturn(List.of(".pk3"));

        var foundWads = wadFileFinder.findWads(testResourcesPath);
        assertTrue(foundWads.containsAll(wads));
        assertEquals(foundWads.size(),wads.size());
    }
}