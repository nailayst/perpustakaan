package com.website.perpustakaan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotBlank(message = "Program Studi tidak boleh kosong")
    private String programStudi;

    @NotBlank(message = "Fakultas tidak boleh kosong")
    private String fakultas;

    @NotNull(message = "Tahun Angkatan harus diisi")
    private Integer tahunAngkatan;

    @NotBlank(message = "Jenis kelamin harus dipilih")
    private String jenisKelamin; // New field for gender

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getProgramStudi() { return programStudi; }
    public void setProgramStudi(String programStudi) { this.programStudi = programStudi; }

    public String getFakultas() { return fakultas; }
    public void setFakultas(String fakultas) { this.fakultas = fakultas; }

    public Integer getTahunAngkatan() { return tahunAngkatan; }
    public void setTahunAngkatan(Integer tahunAngkatan) { this.tahunAngkatan = tahunAngkatan; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
}