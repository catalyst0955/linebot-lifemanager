package line.robot.config;

import line.robot.model.LineBotModal;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface DataRepository extends JpaRepository<LineBotModal,String> {

    List<LineBotModal> findAll();


    List<LineBotModal> findAllByCreateTimeBeforeAndCreateTimeAfter(Date createTimeBefore,Date createTimeAfter);

    List<LineBotModal> findAllByCreateTimeBetween(Date createTimeBefore,Date createTimeAfter);

    @Transactional
    void deleteBySerial(Integer serial);
}
