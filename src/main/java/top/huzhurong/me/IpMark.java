package top.huzhurong.me;

import java.util.Date;

/**
 * @author luobo.cs@raycloud.com
 * @since 2018/8/5
 */
public class IpMark {
    private String ip;
    private String user;
    private String memo;
    private Date addTime;
    private Date updTime;
    private String devType;

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    @Override
    public String toString() {
        return "IpMark{" +
                "ip='" + ip + '\'' +
                ", user='" + user + '\'' +
                ", memo='" + memo + '\'' +
                ", addTime=" + addTime +
                ", updTime=" + updTime +
                ", devType='" + devType + '\'' +
                '}';
    }
}
