package com.aegon.getthresholdvalue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetThresholdValueResponse {
    private Integer isThereError;
    private String errorText;
    private String tarife_no;
    private Integer value;


}
