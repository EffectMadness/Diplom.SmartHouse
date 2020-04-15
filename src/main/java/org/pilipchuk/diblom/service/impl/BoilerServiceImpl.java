package org.pilipchuk.diblom.service.impl;

import org.pilipchuk.diblom.dao.BoilerDao;
import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.service.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class BoilerServiceImpl implements BoilerService {

    @Autowired
    BoilerDao boilerDao;

    @Override
    public Boiler findFirst() {
        return StreamSupport.stream(boilerDao.findAll().spliterator(), false)
                .findFirst().orElse(null);
    }

    @Override
    public void update(Boiler boiler) {
        boilerDao.save(boiler);
    }
}
