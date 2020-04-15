package org.pilipchuk.diblom.controller;

import org.pilipchuk.diblom.dao.SpecDaySetupDao;
import org.pilipchuk.diblom.dao.TimeZoneDao;
import org.pilipchuk.diblom.dto.DaySetupDTO;
import org.pilipchuk.diblom.dto.SpecDayDTO;
import org.pilipchuk.diblom.dto.jtable.JTableEntity;
import org.pilipchuk.diblom.dto.jtable.JTableRequest;
import org.pilipchuk.diblom.dto.jtable.jTableEntityList;
import org.pilipchuk.diblom.entities.DaySetup;
import org.pilipchuk.diblom.entities.SpecDaySetup;
import org.pilipchuk.diblom.entities.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.pilipchuk.diblom.utils.JTableUtil.toResponse;

@RestController
@RequestMapping("/specday-setup")
public class SpecDaySetupController {

    @Autowired
    private SpecDaySetupDao specDaySetupDao;
    @Autowired
    private TimeZoneDao timeZoneDao;

    @PostMapping
    public jTableEntityList<SpecDayDTO> all(JTableRequest jTableRequest) {
        Page<SpecDayDTO> result = specDaySetupDao.findAll(jTableRequest.getPageRequest()).map(SpecDayDTO::new);
        return new jTableEntityList<>("OK", result.getContent(), result.getNumber());
    }

    @PostMapping("/create")
    public JTableEntity create(@ModelAttribute SpecDayDTO dto) {
        return toResponse(() -> {
            TimeZone timeZone = timeZoneDao.findById(dto.getTimeZoneName()).get();
            SpecDaySetup entity = specDaySetupDao.save(new SpecDaySetup().absorb(dto, timeZone));
            return new SpecDayDTO(entity);
        });
    }

    @PostMapping("/update")
    public JTableEntity update(@ModelAttribute SpecDayDTO dto) {
        return toResponse(() -> {
            SpecDaySetup specDaySetup = specDaySetupDao.findById(dto.getId()).orElseThrow(IllegalArgumentException::new);
            TimeZone timeZone = timeZoneDao.findById(dto.getTimeZoneName()).orElseThrow(IllegalArgumentException::new);
            specDaySetupDao.save(specDaySetup.absorb(dto, timeZone));
        });
    }

    @PostMapping("/delete")
    public JTableEntity delete(@RequestParam Integer id) {
        return toResponse(() -> specDaySetupDao.deleteById(id));
    }
}
