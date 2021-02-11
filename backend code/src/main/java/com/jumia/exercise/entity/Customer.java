package com.jumia.exercise.entity;

import com.jumia.exercise.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @Column(columnDefinition = "id")
    private long id;

    private String name;

    private String phone;

    @Transient
    private boolean valid;

    @Transient
    private String countryName;

    @PostLoad
    public void postLoad() {
        for (Country country : Country.values()) {
            if (this.phone.startsWith(country.getCode())) {
                this.countryName = country.name();
                if (this.phone.matches(country.getRegex())) {
                    this.valid = true;
                }
            }
        }
    }
}
