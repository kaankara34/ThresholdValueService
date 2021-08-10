package com.aegon.getthresholdvalue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetThresholdValueRequest implements Serializable {
    private static final long serialVersionUID = -5883262811222969458L;
    private String tarifeNo;
    private Date tarih;
}
