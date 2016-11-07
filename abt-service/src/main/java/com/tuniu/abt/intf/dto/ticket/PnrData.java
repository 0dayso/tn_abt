package com.tuniu.abt.intf.dto.ticket;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by lanlugang on 2016/5/23.
 */
public class PnrData implements Serializable {

    private static final long serialVersionUID = 3851529127172504145L;

    /**
     * pnr
     */
    @NotBlank(message = "{PnrData.pnr.notEmpty}")
    @Pattern(regexp = "[0-9|A-Z]{6}", message = "{PnrData.pnr.invalid}")
    private String pnr;

    /**
     * 航信出票供应商 BSP BOP
     */
    @NotBlank(message = "{PnrData.solution.notEmpty}")
    @Pattern(regexp = "^B[O|S]P$", message = "{PnrData.solution.invalid}")
    private String solution;

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
