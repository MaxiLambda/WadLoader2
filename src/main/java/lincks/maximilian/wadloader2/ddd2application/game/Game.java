package lincks.maximilian.wadloader2.ddd2application.game;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadReadWriteRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Log
@RequiredArgsConstructor
@Service
public class Game {
    public static final String GZDOOM_HOME = "GZDoom_Home";
    private final WadReadWriteRepo wadRepo;
    private final IWadReadWriteRepo iWadRepo;
    public void start(IWad iWad, Wad... wads){
        String[] command =  Stream.concat(
                Stream.of(
                        String.format("\"%s\\gzdoom.exe\"", System.getenv(GZDOOM_HOME)),
                        "-iwad",
                        String.format("\"%s\"", iWad.getPath().getPath()),
                        "-file"
                ),
                Arrays.stream(wads)
                        .sequential()
                        .map(Wad::getPath)
                        .map(WadPath::getPath)
                        .map("\"%s\""::formatted)
        ).toList().toArray(new String[0]);
        try {
            log.info(Arrays.toString(command));
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(WadPack wadPack){
        IWad iWad = iWadRepo.findById(wadPack.getIWad()).orElseThrow(() -> new RuntimeException("No IWad found for WadPack"));

        List<Map.Entry<Integer, WadPath>> wadLoader = new ArrayList<>(wadPack.getWads().entrySet());
        Wad[] wads = wadLoader.stream()
                .map(Map.Entry::getValue)
                .map(wadRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList()
                .toArray(new Wad[]{});
        start(iWad, wads);
    }
}