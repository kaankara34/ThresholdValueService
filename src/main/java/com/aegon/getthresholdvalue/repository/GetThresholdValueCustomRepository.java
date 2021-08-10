package com.aegon.getthresholdvalue.repository;

import java.util.Date;
import java.util.Map;

public interface GetThresholdValueCustomRepository {
    Map<String,Object> getThresholdValue(String tarifeNo,Date tarih);
}
