package com.uber.auth.Authentication.models;

import com.uber.auth.Authentication.enums.CarType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends BaseModel {

    @Column(unique = true, nullable = false)
    private String plateNumber;

    private String brand;

    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @OneToOne
    private Driver driver;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Color color;
}