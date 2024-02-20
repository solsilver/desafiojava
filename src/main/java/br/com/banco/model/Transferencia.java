package br.com.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Table
@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    private BigDecimal valor;
    private String tipo;
    private String nome_Operador_Transacao;
    @ManyToOne
    @JoinColumn(name = "Conta_id")
    private Conta conta;

}
