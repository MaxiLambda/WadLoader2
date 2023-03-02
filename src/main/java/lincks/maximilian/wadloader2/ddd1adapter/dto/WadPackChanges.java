package lincks.maximilian.wadloader2.ddd1adapter.dto;

import java.util.Map;

public record WadPackChanges(Map<Integer, String> order, String packName) {
}
