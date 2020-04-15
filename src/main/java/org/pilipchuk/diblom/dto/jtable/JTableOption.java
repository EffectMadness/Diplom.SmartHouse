package org.pilipchuk.diblom.dto.jtable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class JTableOption {
    @JsonProperty("DisplayText")
    private String DisplayText;
    @JsonProperty("Value")
    private String Value;

}
