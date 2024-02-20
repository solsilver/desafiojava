package br.com.banco.respository;

import br.com.banco.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query(value = "SELECT * FROM transferencia t "+
            "WHERE((:inicioPeriodo IS NULL OR :fimPeriodo IS NULL)"+
            "OR t.data BETWEEN :inicioPeriodo AND :fimPeriodo)"+
            "AND((:nomeOperador IS NULL) OR t.nome_operador_transacao LIKE concat('%',:nomeOperador,'%'))",
            nativeQuery = true)
    List<Transferencia> findAllFilter(
            @Param("inicioPeriodo") LocalDate inicioPeriodo,
            @Param("fimPeriodo") LocalDate fimPeriodo,
            @Param("nomeOperador") String nomeOperador);
}
