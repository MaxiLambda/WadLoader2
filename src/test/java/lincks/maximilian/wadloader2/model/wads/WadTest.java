package lincks.maximilian.wadloader2.model.wads;

import lincks.maximilian.wadloader2.repos.services.WadService;
import lincks.maximilian.wadloader2.repos.services.WadTagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WadTest {
    @Autowired
    WadService wadService;
    WadTagService wadTagService;


    @BeforeEach
    void beforeAll() {
        wadService.deleteAll();
        wadTagService.deleteAll();
    }

    @Test
    void addWadsToDB() {
        List<Path> wadPaths = Stream.of(
                "D:\\Doom\\the\\only\\thing\\they\\fear\\is\\you.wad",
                "D:\\Doom\\ssg\\ssg.pk3"
        ).map(Path::of)
                .map(Path::toAbsolutePath)
                .toList();

        wadPaths.stream().map(Wad::new).forEach(wadService::save);

        //check if all wads were added
        assertEquals(wadService.findAll().size(),wadPaths.size());
        //check if paths match
        assertEquals(
                wadService.findById(wadPaths.get(0).toString()).getPath()
                ,wadPaths.get(0).toString()
        );


    }
}