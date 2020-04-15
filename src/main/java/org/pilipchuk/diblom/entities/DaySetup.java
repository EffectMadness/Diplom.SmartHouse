package org.pilipchuk.diblom.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "day_setup")
public class DaySetup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String day;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "time_zone")
    TimeZone timeZone;
}