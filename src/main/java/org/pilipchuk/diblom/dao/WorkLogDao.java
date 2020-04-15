package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.WorkLog;
import org.springframework.data.repository.CrudRepository;

public interface WorkLogDao extends CrudRepository<WorkLog, Integer> {
}
