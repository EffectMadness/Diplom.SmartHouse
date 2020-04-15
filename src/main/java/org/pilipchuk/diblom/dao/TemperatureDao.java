package org.pilipchuk.diblom.dao;


import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.data.repository.CrudRepository;

public interface TemperatureDao extends CrudRepository<Temperature, Integer>{
}
