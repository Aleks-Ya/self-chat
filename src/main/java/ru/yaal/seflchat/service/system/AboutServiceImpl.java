package ru.yaal.seflchat.service.system;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author Aleksey Yablokov
 */
@Component
@Slf4j
class AboutServiceImpl implements AboutService {

    @Override
    @SneakyThrows
    public String getImplementationVersion() {
        URL url = getClass().getClassLoader().getResource("version.txt");
        String content = null;
        if (url != null) {
            content = Files.lines(Paths.get(url.getFile())).collect(Collectors.joining());
        }
        log.info("version.txt\n{}", content);
        return content;
    }
}
