package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.dao.DaySetupDao;
import org.pilipchuk.diblom.dao.TimeZoneDao;
import org.pilipchuk.diblom.dto.DaySetupDTO;
import org.pilipchuk.diblom.dto.jtable.JTableEntity;
import org.pilipchuk.diblom.dto.jtable.JTableRequest;
import org.pilipchuk.diblom.dto.jtable.jTableEntityList;
import org.pilipchuk.diblom.entities.DaySetup;
import org.pilipchuk.diblom.entities.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.pilipchuk.diblom.utils.JTableUtil.toResponse;

@RestController
@RequestMapping("/day-setup")
public class DaySetupController {

    @Autowired
    private DaySetupDao daySetupDao;
    @Autowired
    private TimeZoneDao timeZoneDao;

    @PostMapping
    public jTableEntityList<DaySetupDTO> all(JTableRequest jTableRequest) {
        Page<DaySetupDTO> result = daySetupDao.findAll(jTableRequest.getPageRequest()).map(DaySetupDTO::new);
        return new jTableEntityList<>("OK", result.getContent(), result.getNumber());
    }

    @PostMapping("/create")
    public JTableEntity create(@ModelAttribute DaySetupDTO dto) {
        return toResponse(() -> {
            TimeZone timeZone = timeZoneDao.findById(dto.getTimeZoneName()).get();
            DaySetup entity = daySetupDao.save(new DaySetup(null, dto.getDay(), timeZone));
            return new DaySetupDTO(entity);
        });
    }

    @PostMapping("/update")
    public JTableEntity update(@ModelAttribute DaySetupDTO dto) {
        return toResponse(() -> {
            DaySetup daySetup = daySetupDao.findById(dto.getId()).orElseThrow(IllegalArgumentException::new);
            TimeZone timeZone = timeZoneDao.findById(dto.getTimeZoneName()).orElseThrow(IllegalArgumentException::new);
            daySetup.setDay(dto.getDay());
            daySetup.setTimeZone(timeZone);
            daySetupDao.save(daySetup);
        });
    }

    @PostMapping("/delete")
    public JTableEntity delete(@RequestParam Integer id) {
        return toResponse(() -> daySetupDao.deleteById(id));
    }
}
