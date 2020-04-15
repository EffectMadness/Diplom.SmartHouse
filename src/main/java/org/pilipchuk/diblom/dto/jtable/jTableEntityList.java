package org.pilipchuk.diblom.dto.jtable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class jTableEntityList<T> {
    @JsonProperty("Result")
    private String result;

    @JsonProperty("Records")
    private List<T> records;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("TotalRecordCount")
    private int totalRecordCount;

    public jTableEntityList(String result, List<T> records, int totalRecordCount) {
        super();
        this.result = result;
        this.records = records;
        this.totalRecordCount = totalRecordCount;
    }
}