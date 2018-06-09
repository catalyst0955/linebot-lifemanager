package line.robot.deadpool;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class DeadPoolCtl {






    private static String createUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path).build()
                .toUriString();
    }
}
