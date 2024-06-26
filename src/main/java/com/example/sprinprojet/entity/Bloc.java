package com.example.sprinprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "Bloc")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bloc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")

    private Long idBloc; // Clé primaire


    private String nomBloc;


    private Long capacitebloc;


    @ManyToOne
    @JsonIgnore
    private Foyer foyer ;


    @OneToMany( cascade = CascadeType.ALL,mappedBy="bloc",fetch=FetchType.EAGER)
    @JsonIgnore

    private Set<Chambre> chambres;

  @OneToMany(cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Reservation> reservations;


}
