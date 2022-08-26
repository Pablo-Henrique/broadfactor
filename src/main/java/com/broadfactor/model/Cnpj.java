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

    @Column(unique = true, nullable = false, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String porte;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String fantasia;

    @Column(nullable = false)
    private String abertura;

    @Column(nullable = false)
    private String natureza_juridica;

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
