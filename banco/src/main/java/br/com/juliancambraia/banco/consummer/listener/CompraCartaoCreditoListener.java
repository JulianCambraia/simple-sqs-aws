package br.com.juliancambraia.banco.consummer.listener;


import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompraCartaoCreditoListener {

    private static final Logger logger = LoggerFactory.getLogger(CompraCartaoCreditoListener.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.fila.compra_cartao_credito_aprovado}")
    private String uriCompraCartaoCreditoAprovada;

    @SqsListener(value = "${cloud.aws.fila.compra_cartao_credito}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void recieveMessage(String message) throws Exception {
        logger.info("Message do SQS Queue - Cartao de Credito {}", message);
        /**
         * Aqui entra a logica de negocio referente a validacao do cartao de credito. Seja um Service ou busca no \
         * banco de dados
         */

        envioMensagemCompraCartaoCreditoAprovada(message);
    }

    public void envioMensagemCompraCartaoCreditoAprovada(String mensagem) {
        queueMessagingTemplate.send(uriCompraCartaoCreditoAprovada, MessageBuilder.withPayload(mensagem).build());
    }


}
