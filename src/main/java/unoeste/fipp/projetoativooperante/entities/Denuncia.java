package unoeste.fipp.projetoativooperante.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "denuncia")
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "den_id")
    private Long id;
    @Column(name = "den_titulo")
    private String titulo;
    @Column(name = "den_texto")
    private String texto;
    @Column(name = "den_urgencia")
    private int urgencia;
    @OneToOne
    @JoinColumn(name = "org_id")
    private Orgao orgao;
    @Column(name = "den_data")
    private LocalDateTime data;
    @OneToOne
    @JoinColumn(name = "tip_id")
    private Tipo tipo;
    @OneToOne
    @JoinColumn(name = "usu_id")
    private Usuario usuario;

    public Denuncia() {
        this(0L,"","",0,null,null,null,null);
    }

    public Denuncia(Long id, String titulo, String texto, int urgencia, Orgao orgao, LocalDateTime data, Tipo tipo, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.urgencia = urgencia;
        this.orgao = orgao;
        this.data = data;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Denuncia(String titulo, String texto, int urgencia, Orgao orgao, LocalDateTime data, Tipo tipo, Usuario usuario) {
        this(0L,titulo,texto,urgencia,orgao,data,tipo,usuario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }

    public Orgao getOrgao() {
        return orgao;
    }

    public void setOrgao(Orgao orgao) {
        this.orgao = orgao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                ", urgencia=" + urgencia +
                ", orgao=" + orgao +
                ", data=" + data +
                ", tipo=" + tipo +
                ", usuario=" + usuario +
                '}';
    }
}
