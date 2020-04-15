package org.pilipchuk.diblom.dto;

import lombok.Data;

@Data
public class BoilerSettingsDTO {
    private Integer status;
    private Integer deltaTime;
    private Integer deltaTemp;
}
