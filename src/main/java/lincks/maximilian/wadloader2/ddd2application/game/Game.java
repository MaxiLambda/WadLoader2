package lincks.maximilian.wadloader2.ddd2application.game;

import lincks.maximilian.wadloader2.ddd1adapter.query.IWadQuery;
import lincks.maximilian.wadloader2.ddd1adapter.query.WadQuery;
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
    public static final String GZDOOM_HOME = "GZDoom_Home";
    private final WadQuery wadwadQuery;
    private final IWadQuery iWadQuery;
    public void start(IWad iWad, Wad... wads){
        String[] command =  Stream.concat(
                Stream.of(
                        String.format("\"%s\\gzdoom.exe\"", System.getenv(GZDOOM_HOME)),
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
        IWad iWad = iWadQuery.getById(wadPack.getIwad()).get();

        List<Map.Entry<Integer,String>> wadLoadorder = new ArrayList<>(wadPack.getWads().entrySet());
        Wad[] wads = wadLoadorder.stream()
                .map(Map.Entry::getValue)
                .map(wadwadQuery::getById)
                .map(Optional::get)
                .toList()
                .toArray(new Wad[]{});
        start(iWad, wads);
    }
}