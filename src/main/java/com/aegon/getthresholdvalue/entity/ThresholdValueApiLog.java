package com.aegon.getthresholdvalue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "threshold_value_api_log")
@Table(name = "threshold_value_api_log")
public class ThresholdValueApiLog implements Serializable {
    private static final long serialVersionUID = 248687438843297736L;

    @Id
   /* @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="THRESHOLD_VALUE_API_LOG_SEQ_GEN")
    @SequenceGenerator(name="THRESHOLD_VALUE_API_LOG_SEQ_GEN", sequenceName = "THRESHOLD_VALUE_API_LOG_SEQ", allocationSize=1)
    private Long logId;
    private String tarifeno;
    private Date inptDate;
    private Integer isThereError;
    private String errMsg;
    private Date logTime;
    private String returnVal;
    private Date dtReturnTime;
    private Integer errorCode;


}
