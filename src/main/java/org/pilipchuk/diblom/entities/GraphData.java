package org.pilipchuk.diblom.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.pilipchuk.diblom.dto.GraphDataDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GraphData {

    private List<String> labels = new ArrayList<>();
    private List<GraphDataDTO> data = new ArrayList<>();

    public Integer addLabel(String label){

        this.labels.add(label);
        return  labels.size();
    }

    public void addData(Integer index, GraphDataDTO data){
        this.data.add(index, data);
    }

    /*
    public Integer getIndexLabelByValue(String label){
        this.labels.forEach(value -> {
            if (value.equals(label)){
                return labels.in
            }
        });
    }
 */
    /*
data: {
        labels: ['10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00'],
        datasets: [{
            label: 'Sensor1',
            backgroundColor: 'rgb(0, 0, 100)',
            borderColor: 'rgb(0, 0, 0)',
            fill: false,
            data: [16.5, 18, 12, 20, 24, 15, 18, 10],
        },{
            label: 'Sensor2',
            backgroundColor: 'rgb(100, 300, 100)',
            borderColor: 'rgb(0, 0, 0)',
            fill: false,
            data: [11, 20, 30, 14, 18, 19, 7, 20],
            }
        ]
    }

 */

}
