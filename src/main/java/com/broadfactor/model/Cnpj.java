package com.broadfactor.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "TB_CNPJ")
public class Cnpj implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cnpj;

    @Column
    private String tipo;

    @Column
    private String porte;

    @Column
    private String nome;

    @Column
    private String fantasia;

    @Column
    private String abertura;

    @Column
    private String natureza_juridica;

    @OneToOne
    @JoinColumn(name = "person_id")
    private User pyshicalPerson;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cnpj cnpj = (Cnpj) o;

        return Objects.equals(id, cnpj.id);
    }

    @Override
    public int hashCode() {
        return 1315560563;
    }
}
