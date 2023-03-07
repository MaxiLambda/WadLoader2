package lincks.maximilian.wadloader2.ddd2application.search.dto;

import java.util.Map;

public record WadPackChanges(Map<Integer, String> order, String packName) {
}
