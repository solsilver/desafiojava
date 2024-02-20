package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.dto.ContaDTO;
import br.com.banco.respository.ContaRepository;
import br.com.banco.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Conta saveConta(Conta conta) {

       contaRepository.save(conta);
        return conta;
    }

    public void salvarNaConta(Long id, Transferencia transferencia) {

        Conta conta = contaRepository.findById(id).get();
        conta.getTransferencias().add(transferencia);
        contaRepository.save(conta);
    }

    public List<ContaDTO> getContas() {
        List<Conta> contas = contaRepository.findAll();
        List<ContaDTO> contasDTOS = contas.stream().map(c -> modelMapper.map(c, ContaDTO.class)).toList();
        return contasDTOS;

    }
    public ContaDTO getContaById(Long id) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("conta nao encontrada") );
        return modelMapper.map(conta,ContaDTO.class);
    }

}
