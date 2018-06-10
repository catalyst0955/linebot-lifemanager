package line.robot.deadpool;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import line.robot.demo.DemoApplication;
import line.robot.demo.LineBotCtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DeadPoolCtl {

    @Autowired
    DeadPoolService deadPoolService;

    @Autowired
    LineBotCtl lineBotCtl;
    public String handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String msg = event.getMessage().getText();
        msg = msg.replaceFirst("deadpool","").trim();
        String srcImageFile = createUri("/static/img/deadpool/1.jpg");
        Path destImageFile = createTempFile("jpg");
        deadPoolService.pressText(msg,srcImageFile,destImageFile,"宋體", Font.BOLD,Color.BLACK,80,0,0,0.0F);
        return destImageFile.toString();
    }



    private static String createUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path).build()
                .toUriString();
    }

    private static Path createTempFile(String ext) {
        String fileName = LocalDateTime.now().toString() + '-' + UUID.randomUUID().toString() + '.' + ext;
        Path tempFile = DemoApplication.deadPoolPath.resolve(fileName);
        tempFile.toFile().deleteOnExit();
        return tempFile;
    }

}
