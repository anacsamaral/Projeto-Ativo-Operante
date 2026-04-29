package unoeste.fipp.projetoativooperante.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;
    @Column(name = "usu_cpf")
    private int cpf;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private int senha;
    @Column(name = "usu_nivel")
    private int nivel;

    public Usuario() {
        this(0L, 0, "",0,1);
    }

    public Usuario(Long id, int cpf, String email, int senha, int nivel) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(int cpf, String email, int senha, int nivel) {
        this(0L, cpf, email, senha, nivel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}

