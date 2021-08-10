package com.aegon.getthresholdvalue.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GetThresholdValueCustomRepositoryImpl implements GetThresholdValueCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Value(value ="${get.threshold.value.procedure.name}")
    private String GET_TRESHOLD_PROCEDURE;
    @Value(value ="${get.threshold.value.procedure.tarifeno}")
    private String TARIFE_NO;
    @Value(value ="${get.threshold.value.procedure.tarih}")
    private String TARIH;
    @Value(value ="${get.threshold.value.procedure.esikdegeri}")
    private String ESIK_DEGERI;
    @Override
    public Map<String, Object> getThresholdValue(String tarifeNo, Date tarih) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery(GET_TRESHOLD_PROCEDURE);
        query.registerStoredProcedureParameter(
                TARIFE_NO,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                TARIH,
                Date.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                ESIK_DEGERI,
                Integer.class,
                ParameterMode.OUT
        );
        query.setParameter(TARIFE_NO, tarifeNo);
        query.setParameter(TARIH, tarih);
        query.execute();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("esikDegeri", query.getOutputParameterValue(ESIK_DEGERI));
        return resultMap;

    }
}
