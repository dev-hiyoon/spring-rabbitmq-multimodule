









```shell
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 --network mariadb-network --restart=unless-stopped rabbitmq:management
```