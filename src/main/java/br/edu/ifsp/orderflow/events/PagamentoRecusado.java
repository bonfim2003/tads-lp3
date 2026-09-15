package br.edu.ifsp.orderflow.events;

import java.time.Instant;

/**
 * Evento que será publicado quando um evento for recusado
 */
public record PagamentoRecusado(
        String pedidoId,
        String motivo,
        Instant ocorridoEm
) implements IDomainEvent {}