package org.pilipchuk.diblom.entities;

import lombok.Data;
import org.pilipchuk.diblom.entity.OperationType;

import javax.persistence.*;
import java.time.Instant;

import static java.time.Instant.now;

@Data
@Entity
@Table(name = "work_log")
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant dateTime;

    @Enumerated(EnumType.STRING)
    private OperationType operation;

    public WorkLog(OperationType operation){
        this.operation = operation;
        dateTime = now();
    }
}
