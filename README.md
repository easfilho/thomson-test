# thomson-test project

Para subir a aplicação
```
./gradlew quarkusDev
```

Para a implementação usei o framework Quarkus (nunca ainda utilizada em uma aplicação, somente algumas POCs) conceitos de clean architecture, onde eu tenho as camadas bem definidas e se precisar mudar alguma delas não vai precisar alterar as outras.
Na camada de apresentação ficaram a implementação dos endpoints e dtos tanto de entrada quanto de saída.
Na camada de negócio ficaram as classe de serviço e a os modelos que representam entidades do mundo real.
Na camada de acesso a dados ficaram as classe de repositório que persistem e buscam dados e também as classes que representam as entidades do banco.implementação

Como boas práticas DevOps
  - Flyway para versionamento das alterações no banco de dados
  - Ferramentas de tolerância a falhas como TimeOut e Circuit Breaker
  - Documentação do Swagger
  - Logs
  - Ratreabilidade com Jaeger
  ```
  Subir Jaeger Docker
            docker run -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 jaegertracing/all-in-one:latest
  ```
        URL Jaeger http://localhost:16686/search
  - Testes de Stress com o Gatling
  - Testes de integração com banco embarcado H2

Para uma próxima etapa ficaria automação dos builds com Jenkis


============================================================================================


This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./gradlew quarkusDev
```

## Packaging and running the application

The application can be packaged using `./gradlew quarkusBuild`.
It produces the `thomson-test-1.0.0-SNAPSHOT-runner.jar` file in the `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/thomson-test-1.0.0-SNAPSHOT-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

## Creating a native executable

You can create a native executable using: `./gradlew buildNative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./gradlew buildNative --docker-build=true`.

You can then execute your native executable with: `./build/thomson-test-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable.
