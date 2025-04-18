package com.liu.edvinasCinemaEvents.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    //todo --
    @OneToMany(mappedBy = "cinema" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Screening> screenings;


}
