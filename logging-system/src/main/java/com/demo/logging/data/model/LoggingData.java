package com.demo.logging.data.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author andrewvella
 * @since 21/05/2017
 */
@Entity
@Table(name = "logging_data", schema = "public", catalog = "banking")
public class LoggingData {
    private Long logId;
    private Long userId;
    private Date logDateTime;
    private String logType;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "log_date_time")
    public Date getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(Date logDateTime) {
        this.logDateTime = logDateTime;
    }

    @Basic
    @Column(name = "log_type")
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoggingData that = (LoggingData) o;

        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (logDateTime != null ? !logDateTime.equals(that.logDateTime) : that.logDateTime != null) return false;
        if (logType != null ? !logType.equals(that.logType) : that.logType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId != null ? logId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (logDateTime != null ? logDateTime.hashCode() : 0);
        result = 31 * result + (logType != null ? logType.hashCode() : 0);
        return result;
    }
}
