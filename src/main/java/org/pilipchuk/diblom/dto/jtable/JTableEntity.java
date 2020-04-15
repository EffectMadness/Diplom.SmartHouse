package org.pilipchuk.diblom.dto.jtable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class JTableEntity<T> {
    @JsonProperty("Result")
    private String result;

    @JsonProperty("Record")
    private T record;

    @JsonProperty("Message")
    private String message;

    public JTableEntity(String result, T record) {
        this.result = result;
        this.record = record;
    }
}