package com.tuniu.abt.intf.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the abt_job_item_execution_context database table.
 */
@Table(name = "abt_job_item_execution_context")
public class AbtJobItemExecutionContext implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_id")
    private Long entryId;

    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "retry_count")
    private int retryCount;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;


    public AbtJobItemExecutionContext() {
    }

    public AbtJobItemExecutionContext(Long entryId, Long jobId, int retryCount) {
        this(entryId, jobId, retryCount, new Date(), new Date());
    }

    public AbtJobItemExecutionContext(Long entryId, Long jobId, int retryCount, Date addTime, Date updateTime) {
        this.entryId = entryId;
        this.jobId = jobId;
        this.retryCount = retryCount;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntryId() {
        return this.entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public Long getJobId() {
        return this.jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}