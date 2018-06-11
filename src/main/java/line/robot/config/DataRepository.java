package line.robot.config;

import line.robot.model.LineBotModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<LineBotModal,String> {
    List<LineBotModal> findAll();


}
