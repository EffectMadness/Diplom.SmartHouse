package org.pilipchuk.diblom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GraphDataDTO {
    private String label;
    private String backgroundColor;
    private String borderColor;
    private Boolean fill;
    private List<Double> data = new ArrayList<>();


    public GraphDataDTO(String label, String backgroundColor, String borderColor, Boolean fill) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.fill = fill;
    }

    public void addData(Double data){
        this.data.add(data);
    }
/*
            label: 'Sensor1',
            backgroundColor: 'rgb(0, 0, 100)',
            borderColor: 'rgb(0, 0, 0)',
            fill: false,
            data: [16.5, 18, 12, 20, 24, 15, 18, 10]
 */
}
