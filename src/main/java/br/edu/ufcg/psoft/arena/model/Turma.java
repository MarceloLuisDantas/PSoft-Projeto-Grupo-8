package br.edu.ufcg.psoft.arena.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String codigo;

    private String periodoLetivo;

    private String professorResponsavel;

    @Enumerated(EnumType.STRING)
    private StatusTurma status = StatusTurma.ATIVA;

    @ManyToMany
    @JoinTable(name = "turma_estudante",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "estudante_id"))
    private Set<Estudante> estudantes = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getPeriodoLetivo() { return periodoLetivo; }
    public void setPeriodoLetivo(String periodoLetivo) { this.periodoLetivo = periodoLetivo; }

    public String getProfessorResponsavel() { return professorResponsavel; }
    public void setProfessorResponsavel(String professorResponsavel) { this.professorResponsavel = professorResponsavel; }

    public StatusTurma getStatus() { return status; }
    public void setStatus(StatusTurma status) { this.status = status; }

    public Set<Estudante> getEstudantes() { return estudantes; }
    public void setEstudantes(Set<Estudante> estudantes) { this.estudantes = estudantes; }
}
