package br.com.banco.model.dto;

import br.com.banco.model.Transferencia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ContaDTO {
    private String nome;
    private List<TransferenciaDTO> transferencias;
}
