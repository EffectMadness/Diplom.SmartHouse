package org.pilipchuk.diblom.controller;

import org.modelmapper.ModelMapper;
import org.pilipchuk.diblom.dto.BoilerSettingsDTO;
import org.pilipchuk.diblom.entities.Boiler;
import org.pilipchuk.diblom.service.BoilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boiler-setup")
public class BoilerController {

    @Autowired
    private BoilerService boilerService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public BoilerSettingsDTO index() {
        Boiler boiler = boilerService.findFirst();
        return mapper.map(boiler, BoilerSettingsDTO.class);
    }

    @PostMapping
    public BoilerSettingsDTO update(BoilerSettingsDTO dto) {
        Boiler boiler = boilerService.findFirst();
        boiler.setStatus(dto.getStatus());
        boiler.setDeltaTime(dto.getDeltaTime());
        boiler.setDeltaTemp(dto.getDeltaTemp());
        boilerService.update(boiler);
        return dto;
    }

}
