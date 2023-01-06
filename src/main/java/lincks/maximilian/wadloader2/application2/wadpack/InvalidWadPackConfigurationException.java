package lincks.maximilian.wadloader2.application2.wadpack;

import lincks.maximilian.wadloader2.domain3.rules.WadPackRule;

import java.util.List;

public class InvalidWadPackConfigurationException extends Exception{

    private InvalidWadPackConfigurationException(String message) {
        super(message);
    }

    public static InvalidWadPackConfigurationException withBrokenRules(List<WadPackRule> brokenRules){
        StringBuilder bob = new StringBuilder("Broken rules:\n");
        brokenRules.stream()
                .map(Object::toString)
                .forEach(bob::append);
        return new InvalidWadPackConfigurationException(bob.toString());
    }

}
