package br.com.banco.service;


import br.com.banco.model.Transferencia;
import br.com.banco.model.dto.TransferenciaDTO;
import br.com.banco.respository.TransferenciaRepository;
import br.com.banco.service.exception.DataInvalida;
import br.com.banco.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private ContaService contaService;
    @Autowired
    private ModelMapper modelMapper;

    public Transferencia fazerTransferencia(Transferencia transferencia) {
        Long id = transferencia.getConta().getId();
        if (contaService.getContaById(id) != null) {
            transferenciaRepository.save(transferencia);
            contaService.salvarNaConta(id, transferencia);
        } else {
            new ObjectNotFoundException("usuario nao encontrado");
        }
        return new Transferencia();
    }

    public List<TransferenciaDTO> getTransferencias(LocalDate inicioPeriodo, LocalDate fimPeriodo, String nomeOperador) {
        List<Transferencia> transferencias = transferenciaRepository.findAllFilter(inicioPeriodo, fimPeriodo, nomeOperador);
        List<TransferenciaDTO> transferenciaDTOS = transferencias.stream().map(t -> modelMapper.map(t, TransferenciaDTO.class)).toList();
        LocalDate now = LocalDate.now();
        if (((fimPeriodo == null) && (inicioPeriodo == null))||((fimPeriodo == null) && (inicioPeriodo != null))) {
            return transferenciaDTOS;
        }
        if ((fimPeriodo != null) && (inicioPeriodo == null)) {
            if (fimPeriodo.isAfter(now)) {
                throw new DataInvalida("Data Invalida");
            }
            else{
            return transferenciaDTOS;
            }
        }
        if ((fimPeriodo != null) && (inicioPeriodo != null)) {
            if ((inicioPeriodo.isAfter(fimPeriodo))) {
                 throw new DataInvalida("Data Invalida");
            }
            else {
                return transferenciaDTOS;
            }

        }


        return null;

    }
}

