package org.pilipchuk.diblom.dto.jtable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JTableOptionsList {
    @JsonProperty("Result")
    private String Result;
    @JsonProperty("Options")
    private List<JTableOption> Options;
}
