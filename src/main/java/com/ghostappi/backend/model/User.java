package com.ghostappi.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String name;
    private String lastName;
    @NotBlank
    @Size(min = 1, max =150)
    private String email;
    private String password;
    private String phone;
    private String gender;
    private boolean status;
    private String bornDate;
    private boolean isCostumer;

    public User() {
    }

    public User(int idUser, String name, String lastName, String email, String password, String phone, String gender,
        boolean status, String bornDate, boolean isCostumer) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.status = status;
        this.bornDate = bornDate;
        this.isCostumer = isCostumer;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMail() {
        return email;
    }
    public void setMail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

        public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }
 
    public boolean isCustumer() {
        return isCostumer;
    }

    public void setCustumer(boolean isCustumer) {
        this.isCostumer = isCustumer;
    }

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", name=" + name + ", lastName=" + lastName + ", mail=" + email + ", password="
                + password + ", phone=" + phone + ", gender=" + gender + ", status=" + status + ", bornDate=" + bornDate
                + ", isCustumer=" + isCostumer + "]";
    }



}
