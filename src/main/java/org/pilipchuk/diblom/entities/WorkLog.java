package org.pilipchuk.diblom.entities;

import lombok.Data;
import org.pilipchuk.diblom.entity.OperationType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "work_log")
public class WorkLog {
    @Id
    private Integer id;
    private String dateTime;
    @Enumerated(EnumType.STRING)
    private OperationType operation;
}
