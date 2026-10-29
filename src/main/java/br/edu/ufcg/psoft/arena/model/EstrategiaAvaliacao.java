package br.edu.ufcg.psoft.arena.model;

import jakarta.persistence.*;

@Entity
public class EstrategiaAvaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoEstrategia tipo;

    @Column(length = 5000)
    private String descricao;

    @Column(length = 10000)
    private String prompt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoEstrategia getTipo() { return tipo; }
    public void setTipo(TipoEstrategia tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}
