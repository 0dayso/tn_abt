package com.tuniu.abt.intf.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "abt_tracer_log_detail")
public class AbtTracerLogDetail {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 输入报文
     */
    @Column(name = "input_param")
    private String inputParam;

    /**
     * 输出报文
     */
    @Column(name = "output_result")
    private String outputResult;

    public AbtTracerLogDetail(Long id, String inputParam, String outputResult) {
        this.id = id;
        this.inputParam = inputParam;
        this.outputResult = outputResult;
    }

    public AbtTracerLogDetail() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取输入报文
     *
     * @return input_param - 输入报文
     */
    public String getInputParam() {
        return inputParam;
    }

    /**
     * 设置输入报文
     *
     * @param inputParam 输入报文
     */
    public void setInputParam(String inputParam) {
        this.inputParam = inputParam == null ? null : inputParam.trim();
    }

    /**
     * 获取输出报文
     *
     * @return output_result - 输出报文
     */
    public String getOutputResult() {
        return outputResult;
    }

    /**
     * 设置输出报文
     *
     * @param outputResult 输出报文
     */
    public void setOutputResult(String outputResult) {
        this.outputResult = outputResult == null ? null : outputResult.trim();
    }
}