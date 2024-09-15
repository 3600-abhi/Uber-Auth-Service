package com.uber.auth.Authentication.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Color extends BaseModel {
    @Column(unique = true, nullable = false)
    private String name;
}