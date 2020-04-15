package org.pilipchuk.diblom.dao;

import org.pilipchuk.diblom.entities.Boiler;
import org.springframework.data.repository.CrudRepository;

public interface BoilerDao extends CrudRepository<Boiler, Integer> {
}
