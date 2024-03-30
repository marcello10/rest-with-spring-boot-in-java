package br.com.marcelo.restwithspringbootinjava.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class BookVO extends RepresentationModel<BookVO> implements Serializable {
    private long id;
    private String autor;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataLancamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private Double preco;
    private String titulo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return id == bookVO.id && Objects.equals(autor, bookVO.autor) && Objects.equals(dataLancamento, bookVO.dataLancamento) && Objects.equals(preco, bookVO.preco) && Objects.equals(titulo, bookVO.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, autor, dataLancamento, preco, titulo);
    }
}
