aws --endpoint-url http://localhost:9324 sqs create-queue --queue-name compra_cartao_credito
aws --endpoint-url http://localhost:9324 sqs create-queue --queue-name compra_cartao_credito_aprovado
aws --endpoint-url http://localhost:9324 sqs send-message --queue-url http://localhost:9324/queue/compra_cartao_credito --message-body "{'numeroCartao': 123456478910 , 'valor': 543,00}"