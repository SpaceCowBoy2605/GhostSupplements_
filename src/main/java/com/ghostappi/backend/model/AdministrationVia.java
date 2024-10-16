package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "administrationvia")
public class AdministrationVia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdministrationVia")
    @JsonProperty("idAdministrationVia")
    private Integer idAdministrationVia;

    @NotBlank(message = "The name must no be null and containn at least one character")
    @Size(min = 1, max = 50, message = "The name must be almost 1 character and 50 characters at most")
    private String name;

    public AdministrationVia() {
    }

    public AdministrationVia(Integer idAdministrationVia, String name) {
        this.idAdministrationVia = idAdministrationVia;
        this.name = name;
    }

    public Integer getIdAdministrationVia() {
        return idAdministrationVia;
    }

    public void setIdAdministrationVia(Integer idAdministrationVia) {
        this.idAdministrationVia = idAdministrationVia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
