package line.robot.ctrl;


import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import line.robot.DemoApplication;
import line.robot.config.DataRepository;
import line.robot.model.LineBotModal;
import line.robot.service.DeadPoolService;
import line.robot.service.LineBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@LineMessageHandler
public class LineBotCtl {


    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Autowired
    private LineBotService lineBotService;

    @Autowired
    private DeadPoolService deadPoolService;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private DataRepository dataRepository;

    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        String msg = event.getMessage().getText();

        if (msg.equals("!指令")) {
           String keyList = lineBotService.replyKeyList(event);
            replyText(event.getReplyToken(), keyList);

        } else if (msg.startsWith("deadpool")) {
            msg = msg.replaceFirst("deadpool","").trim();
            //String srcImageFile = createUri("/static/img/deadpool/1.jpg");
            InputStream input = null;
            try {
                input = new ClassPathResource("/static/img/deadpool/"+getRandom()+".jpg").getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Path destImageFile = createTempFile("jpg");

            deadPoolService.pressText(msg,input,destImageFile, Font.TRUETYPE_FONT,Color.YELLOW,20,0,10,0.9F);

            reply(event.getReplyToken(), new ImageMessage(createUri("/deadpool/"+destImageFile.getFileName()), createUri("/deadpool/"+destImageFile.getFileName())));
        }else if(msg.startsWith("-add")){
            String[] strList = msg.trim().split(" ");
            if(strList.length!=3){
                replyText(event.getReplyToken(), "指令格式錯誤，請再輸入一次(-add 指令 Imgur圖片網址)");
            }else{
                replyText(event.getReplyToken(),lineBotService.addValue(strList[1],strList[2],event.getSource().getUserId()));
            }

        }
        else if(msg.length()<=10){
            String fromServicePic = lineBotService.getPic(msg);
            if (!fromServicePic.isEmpty()) {
                reply(event.getReplyToken(), new ImageMessage(fromServicePic, fromServicePic));
            }
        }

    }


    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }


    private void handleHeavyContent(String replyToken, String messageId,
                                    Consumer<MessageContentResponse> messageConsumer) {
        final MessageContentResponse response;
        try {
            response = lineMessagingClient.getMessageContent(messageId)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            reply(replyToken, new TextMessage("Cannot get image: " + e.getMessage()));
            throw new RuntimeException(e);
        }
        messageConsumer.accept(response);
    }


    private static String createUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path).build()
                .toUriString();
    }


    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void replyText(@NonNull String replyToken, @NonNull String message) {
        if (replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken must not be empty");
        }
        if (message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "……";
        }
        this.reply(replyToken, new TextMessage(message));
    }

    private static Path createTempFile(String ext) {
        String fileName = LocalDateTime.now().toString() + '-' + UUID.randomUUID().toString() + '.' + ext;
        Path tempFile = DemoApplication.deadPoolPath.resolve(fileName);
        tempFile.toFile().deleteOnExit();
        return tempFile;
    }

    private static String getRandom(){
        Random random = new Random();
        int  i = random.nextInt(17)+1;
        return String.valueOf(i);
    }

}
