# Getting Started

### Criando container MongoDb
```
docker run -v ~/develop/docker/mongodb --name mongodb -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mayke -e MONGO_INITDB_ROOT_PASSWORD="jesus" mongo
```
### Conectando ao Banco de dados
```
mongodb://mayke:jesus@localhost:27017/carteira-do-aposentado
```

### Start container Mongo
```
docker start mongodb
```
### Run
##### pmd / checkStyle
```
win: .\mvnw clean install  linux: mvn clean install
```
##### pmd / checkStyle result
```
/target/checksyle-result.xml
```
##### swagger
```
http://localhost:6030/swagger-ui.html
```
#### Monitoramento
##### actuator
```
www.DOMAN:PORT/actuator
```

##### Modelo docker
```
https://gist.github.com/viniciusxyz/3027265e046d405a2a5ea05189f35393#file-react-spring-boot-mongodb
```