package line.robot.model;


import javax.persistence.*;

@Entity
@Table(name = "line_robot")
public class LineBotModal {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String DataKey;

    @Column(nullable = false)
    private String DataValue;

    @Column
    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }


    public String getDataKey() {
        return DataKey;
    }

    public void setDataKey(String dataKey) {
        DataKey = dataKey;
    }

    public String getDataValue() {
        return DataValue;
    }

    public void setDataValue(String dataValue) {
        DataValue = dataValue;
    }

}
