package line.robot.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounting_line_robot")
public class LineBotModal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer serial;

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
    private String userId;

    @Column
    private Date createTime;

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
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }
}
