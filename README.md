# Spring-RabbitMQ


## Docker RabbitMQ
```shell
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --restart=unless-stopped rabbitmq:management --network mariadb-network
```

## Module

### RabbitMQ PubSub(Publish-Subscribe)

publisher에서 consumer에게 요청을 보냅니다.(publisher -> consumer)
consumer에서는 해당 요청을 받아 이후 로직을 수행합니다. consumer에서는 retry(실패 재시도 3회), concurrency(동시처리, 10개) 전략을 사용합니다.

* rabbitmq 설정
  * exchange: SAMPLE.EX01, direct
  * queue: SAMPLE.QUE01
  * routing key: RKEY.EX01.#

* 관련모듈
  * module-common
  * module-publisher
  * module-consumer


### RabbitMQ RPC(Request-Reply)

server에서 client에 요청 후 client 응답을 다시 server에서 처리합니다. (server <-> client)

* rabbitmq 설정
  * exchange: SAMPLE.EX02, direct
  * queue: SAMPLE.QUE02
  * routing key: RKEY.EX02.RPC
* 관련모듈
  * module-common
  * module-server
  * module-client