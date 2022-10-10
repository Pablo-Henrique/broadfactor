package com.broadfactor.model;

import com.broadfactor.enums.StatusEmail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_EMAIL")
public class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = 1600177909450914363L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ownerRef;

    @Column
    private String emailFrom;

    @Column
    private String emailTo;

    @Column
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column
    private LocalDateTime sendDateEmail;

    @Column
    @Enumerated
    private StatusEmail statusEmail;
}