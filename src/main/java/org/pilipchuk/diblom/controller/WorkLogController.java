package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.dao.WorkLogDao;
import org.pilipchuk.diblom.entities.WorkLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work-log")
public class WorkLogController {

    @Autowired
    private WorkLogDao workLogDao;

    @GetMapping
    public Iterable<WorkLog> get() {
        return workLogDao.findAll();
    }

}
