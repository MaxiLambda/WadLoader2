package lincks.maximilian.wadloader2.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
@Setter
@ConfigurationProperties("wads.extensions")
public class WadExtensionConfiguration {

    private List<String> allowed;
}
