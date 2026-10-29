package br.edu.ufcg.psoft.arena.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(optional = false)
    private Turma turma;

    @ManyToOne(optional = false)
    private Problema problema;

    @ManyToMany
    private Set<EstrategiaAvaliacao> estrategias = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public Problema getProblema() { return problema; }
    public void setProblema(Problema problema) { this.problema = problema; }

    public Set<EstrategiaAvaliacao> getEstrategias() { return estrategias; }
    public void setEstrategias(Set<EstrategiaAvaliacao> estrategias) { this.estrategias = estrategias; }
}
