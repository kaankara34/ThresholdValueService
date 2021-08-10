package com.aegon.getthresholdvalue.repository;

import com.aegon.getthresholdvalue.entity.ThresholdValueApiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThresholdValueApiLogRepository extends JpaRepository<ThresholdValueApiLog, Long> {

}
