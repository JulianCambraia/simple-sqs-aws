package br.com.juliancambraia.venda.controller;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
@RequiredArgsConstructor
public class CartaoCreditoResource {
    private static final Logger logger = LoggerFactory.getLogger(CartaoCreditoResource.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.fila.compra_cartao_credito}")
    private String uriCompraCartaoCredito;

    @PostMapping
    public ResponseEntity<String> consultarCartaoCredito(@RequestBody String mensagem) {
        logger.info("SQS Queue - cartão de crédito - {}", mensagem);
        queueMessagingTemplate.send(uriCompraCartaoCredito, MessageBuilder.withPayload(mensagem).build());
        return ResponseEntity.ok("Solicitação enviada com sucesso.");

    }

}
