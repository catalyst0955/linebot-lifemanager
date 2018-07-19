package line.robot.model;


import javax.persistence.*;

@Entity
@Table(name = "accounting_line_robot")
public class LineBotModal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String serial;

    @Column
    private String payName;

    @Column(nullable = false)
    private String payValue;

    @Column
    private String firstClassify;

    @Column
    private String secondClassify;

    @Column
    private String command;

    @Column
    private String UserId;

    @Column
    private String CreateTime;

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayValue() {
        return payValue;
    }

    public void setPayValue(String payValue) {
        this.payValue = payValue;
    }

    public String getFirstClassify() {
        return firstClassify;
    }

    public void setFirstClassify(String firstClassify) {
        this.firstClassify = firstClassify;
    }

    public String getSecondClassify() {
        return secondClassify;
    }

    public void setSecondClassify(String secondClassify) {
        this.secondClassify = secondClassify;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
