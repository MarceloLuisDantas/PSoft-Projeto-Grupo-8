package br.edu.ufcg.psoft.arena.model;

import jakarta.persistence.*;

@Entity
public class Problema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 5000)
    private String descricao;

    @Column(length = 5000)
    private String requisitosFuncionais;

    @Column(length = 5000)
    private String requisitosProjeto;

    @Enumerated(EnumType.STRING)
    private StatusProblema status = StatusProblema.ATIVO;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getRequisitosFuncionais() { return requisitosFuncionais; }
    public void setRequisitosFuncionais(String requisitosFuncionais) { this.requisitosFuncionais = requisitosFuncionais; }

    public String getRequisitosProjeto() { return requisitosProjeto; }
    public void setRequisitosProjeto(String requisitosProjeto) { this.requisitosProjeto = requisitosProjeto; }

    public StatusProblema getStatus() { return status; }
    public void setStatus(StatusProblema status) { this.status = status; }
}
