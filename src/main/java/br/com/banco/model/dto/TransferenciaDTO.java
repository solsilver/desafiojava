package br.com.banco.model.dto;

import br.com.banco.model.Conta;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
public class TransferenciaDTO {
    private Long id;
    private LocalDate data = LocalDate.now();
    private BigDecimal valor;
    private String tipo;
    private String nome_Operador_Transacao;
    private Conta conta;
}
