package com.aegon.getthresholdvalue.controller;

import com.aegon.getthresholdvalue.model.GetThresholdValueRequest;
import com.aegon.getthresholdvalue.model.GetThresholdValueResponse;
import com.aegon.getthresholdvalue.service.GetTresholdValueService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@AllArgsConstructor
public class GetThresholdController {


    private final GetTresholdValueService getTresholdValueService;

    @GetMapping("ThresholdValue/api/v1/GetThresholdValue")
    public ResponseEntity<GetThresholdValueResponse> getThresholdValue(@RequestParam(value = "tarife_no") String tarifeNo,
                                                                       @RequestParam(value = "tarih") String tarih) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = inputFormat.parse(tarih);
        GetThresholdValueRequest getThresholdValueRequest = new GetThresholdValueRequest(tarifeNo, date);
        GetThresholdValueResponse responseBag = getTresholdValueService.getTresholdValueResponse(getThresholdValueRequest);
        return ResponseEntity.ok(responseBag);

    }


}
