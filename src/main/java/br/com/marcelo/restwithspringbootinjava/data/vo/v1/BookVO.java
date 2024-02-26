package br.com.marcelo.restwithspringbootinjava.data.vo.v1;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class BookVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "author")
    private String autor;
    @Column(name = "launch_date",nullable = false)
    private LocalDate dataLancamento;
    @Column(name = "price",nullable = false)
    private Double preco;
    @Column(name = "title")
    private String titulo;


}
