package com.algaworks.sistemaprodutos;

import com.algaworks.sistemaprodutos.model.Produto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class ChamandoStoredProcedures {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Produtos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        consultaComProcedures(entityManager);
//        pesquisaComProcedures(entityManager);
        atualizandoComProcedures(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void atualizandoComProcedures(EntityManager entityManager) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("ajustar_preco_produto");

        storedProcedureQuery.registerStoredProcedureParameter(
                "produto_id", Integer.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter(
                "percentual_ajuste", BigDecimal.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter(
                "preco_ajustado", BigDecimal.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 1);
        storedProcedureQuery.setParameter("percentual_ajuste", new BigDecimal(1.1));

        storedProcedureQuery.execute();

        BigDecimal precoAjustado = (BigDecimal) storedProcedureQuery
                .getOutputParameterValue("preco_ajustado");

        log("Preço ajustado: " + precoAjustado);

    }

    public static void pesquisaComProcedures(EntityManager entityManager) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("pesquisar_produtos", Produto.class);

        storedProcedureQuery.registerStoredProcedureParameter(
                "termo", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("termo", "C");

        storedProcedureQuery.execute();

        List<Produto> lista = storedProcedureQuery.getResultList();

        lista.forEach(p -> log("Produto => Id: " + p.getId() + ", Nome: " + p.getNome()));
    }

    private static void consultaComProcedures(EntityManager entityManager) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("nome_produto");

        storedProcedureQuery.registerStoredProcedureParameter(
                "produto_id", Integer.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter(
                "produto_nome", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 1);

        storedProcedureQuery.execute();

        String nome = (String) storedProcedureQuery
                .getOutputParameterValue("produto_nome");

        log("Nome produto: " + nome);
    }

    private static void log(Object msg) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + msg);
    }
}
