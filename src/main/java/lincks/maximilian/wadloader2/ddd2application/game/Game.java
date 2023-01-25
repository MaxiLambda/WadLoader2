package lincks.maximilian.wadloader2.ddd2application.game;

import lincks.maximilian.wadloader2.ddd3domain.repos.IWadRepo;
import lincks.maximilian.wadloader2.ddd3domain.repos.WadRepo;
import lincks.maximilian.wadloader2.ddd3domain.wads.IWad;
import lincks.maximilian.wadloader2.ddd3domain.wads.Wad;
import lincks.maximilian.wadloader2.ddd3domain.wads.WadPack;
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

    private final WadRepo wadRepo;
    private final IWadRepo iWadRepo;
    public void start(IWad iWad, Wad... wads){
        String[] command =  Stream.concat(
                Stream.of(
                        String.format("\"%s\\gzdoom.exe\"", System.getenv("GZDoom_Home")),
                        "-iwad",
                        String.format("\"%s\"", iWad.getPath()),
                        "-file"
                ),
                Arrays.stream(wads)
                        .sequential()
                        .map(Wad::getPath)
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
        IWad iWad = iWadRepo.findById(wadPack.getIwad()).get();

        List<Map.Entry<Integer,String>> wadLoadorder = new ArrayList<>(wadPack.getWads().entrySet());
        Wad[] wads = wadLoadorder.stream()
                .map(Map.Entry::getValue)
                .map(wadRepo::findById)
                .map(Optional::get)
                .toList()
                .toArray(new Wad[]{});
        start(iWad, wads);
    }
}