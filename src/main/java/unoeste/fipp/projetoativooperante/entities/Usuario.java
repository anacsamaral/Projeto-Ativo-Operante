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
    private String cpf;
    @Column(name = "usu_email")
    private String email;
    @Column(name = "usu_senha")
    private String senha;
    @Column(name = "usu_nivel")
    private String nivel;

    public Usuario() {
        this(0L, "", "","","");
    }

    public Usuario(Long id, String cpf, String email, String senha, String nivel) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario(String cpf, String email, String senha, String nivel) {
        this(0L, cpf, email, senha, nivel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
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

