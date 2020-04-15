package org.pilipchuk.diblom.controller;

import org.modelmapper.ModelMapper;
import org.pilipchuk.diblom.dto.TemperatureDTO;
import org.pilipchuk.diblom.entities.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {
}
