package com.aegon.getthresholdvalue.service.impl;

import com.aegon.getthresholdvalue.entity.ThresholdValueApiLog;
import com.aegon.getthresholdvalue.model.GetThresholdValueRequest;
import com.aegon.getthresholdvalue.model.GetThresholdValueResponse;
import com.aegon.getthresholdvalue.repository.GetThresholdValueCustomRepository;
import com.aegon.getthresholdvalue.repository.ThresholdValueApiLogRepository;
import com.aegon.getthresholdvalue.service.GetTresholdValueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class GetTresholdValueServiceImpl implements GetTresholdValueService {

    private static final String ESIK_DEGERI_NOT_FOUND = "GET_THRESHOLDVALUE prosedürü -1 döndü. ";
    private static final String CATCH_BLOCK = "Serviste catch'e düştü.";

    private final GetThresholdValueCustomRepository getThresholdValueCustomRepository;
    private final ThresholdValueApiLogRepository thresholdValueApiLogRepository;


    @Override
    public GetThresholdValueResponse getTresholdValueResponse(GetThresholdValueRequest getThresholdValueRequest) {

        GetThresholdValueResponse getThresholdValueResponse = new GetThresholdValueResponse();
        getThresholdValueResponse.setTarife_no(getThresholdValueRequest.getTarifeNo());
        Integer value = 0;

        try {

            Map<String, Object> thresholdValue = getThresholdValueCustomRepository.getThresholdValue(getThresholdValueRequest.getTarifeNo(), getThresholdValueRequest.getTarih());
            value = Integer.parseInt(thresholdValue.get("esikDegeri").toString());
            getThresholdValueResponse.setValue(value);

            if (value != -1) {
                saveThresholdValueApiLog(getThresholdValueRequest, new Date(), 0, null, null, value.toString());
                getThresholdValueResponse.setIsThereError(0);
                return getThresholdValueResponse;
            } else {
                log.error("errorCode:-1, isThereError:1, errorMessage:{}, returnTime:{}", ESIK_DEGERI_NOT_FOUND, new Date());
                saveThresholdValueApiLog(getThresholdValueRequest, new Date(), 1, -1, ESIK_DEGERI_NOT_FOUND, value.toString());
                getThresholdValueResponse.setIsThereError(1);
                getThresholdValueResponse.setErrorText("Gönderdiğiniz tarifeye ait kayıt bulunamadı.");
                return getThresholdValueResponse;
            }


        } catch (Exception ex) {

            String err = CATCH_BLOCK + ex.getMessage();
            if (err.length() > 500) {
                err = err.substring(0, 500);

            }
            log.error("errorCode:-2, isThereError:1, errorMessage:{}, returnTime:{}", CATCH_BLOCK, new Date());
            saveThresholdValueApiLog(getThresholdValueRequest, new Date(), 1, -2, err, value.toString());
            getThresholdValueResponse.setIsThereError(1);
            getThresholdValueResponse.setErrorText("Beklenmeyen bir hata oluştu.");
            return getThresholdValueResponse;


        }


    }

    private void saveThresholdValueApiLog(GetThresholdValueRequest getThresholdValueRequest, Date dtReturnTime, Integer isThereError, Integer errorCode, String errMsg, String returnVal) {
        ThresholdValueApiLog thresholdValueApiLog = new ThresholdValueApiLog();
        thresholdValueApiLog.setLogTime(new Date());
        thresholdValueApiLog.setTarifeno(getThresholdValueRequest.getTarifeNo());
        thresholdValueApiLog.setInptDate(getThresholdValueRequest.getTarih());
        thresholdValueApiLog.setDtReturnTime(dtReturnTime);
        thresholdValueApiLog.setIsThereError(isThereError);
        thresholdValueApiLog.setErrorCode(errorCode);
        thresholdValueApiLog.setErrMsg(errMsg);
        thresholdValueApiLog.setReturnVal(returnVal);
        thresholdValueApiLogRepository.save(thresholdValueApiLog);

    }


}
