package douglas.develop.core.dto;

import douglas.develop.core.model.Curso;

public class CursoDTO {

    private Long id;
    private String titulo;

    public CursoDTO() {}

    public CursoDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.titulo = curso.getTitulo();
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
}
