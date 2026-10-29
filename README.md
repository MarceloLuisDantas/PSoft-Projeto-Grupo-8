# PSoft Arena - Backend

Backend em Java 21 + Spring Boot 3 + Gradle, com banco H2 em arquivo (pasta `./data`).

## Dependências para rodar

| Ferramenta | Versão | Observação |
|------------|--------|------------|
| JDK (Java) | 21 | Obrigatório |
| Gradle | 8.x | Necessário só na primeira vez, para gerar o `./gradlew` |
| Git | qualquer | Para clonar o repositório |

As bibliotecas do projeto (Spring Web, Spring Data JPA, Validation, H2, springdoc-openapi/Swagger)
são baixadas automaticamente pelo Gradle no primeiro build — é preciso ter acesso à internet.

## Instalando as dependências

### Linux (Ubuntu/Debian)

```bash
sudo apt update
sudo apt install -y openjdk-21-jdk git
# Gradle via SDKMAN (recomendado, a versão do apt costuma ser antiga)
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install gradle 8.10.2
```

### Windows / macOS

- JDK 21: https://adoptium.net (Temurin 21)
- Gradle: https://gradle.org/install (ou `brew install gradle` no macOS)

Confira as instalações:

```bash
java -version    # deve mostrar 21
gradle -v
```

## Compilando

Na raiz do projeto:

```bash
gradle wrapper          # apenas na primeira vez: gera ./gradlew
./gradlew build         # baixa as bibliotecas, compila e roda os testes
```

No Windows, use `gradlew.bat` no lugar de `./gradlew`.

O `.jar` gerado fica em `build/libs/`.

## Rodando

```bash
./gradlew bootRun
```

ou, a partir do jar compilado:

```bash
java -jar build/libs/psoft-arena-0.0.1-SNAPSHOT.jar
```

A aplicação sobe em http://localhost:8080.

- Swagger UI: http://localhost:8080/swagger-ui.html
- Console H2: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:file:./data/arena`, usuário `sa`, sem senha)

Para zerar o banco, pare a aplicação e apague a pasta `data/`.

## Endpoints implementados

| US | Recurso | Principais rotas |
|----|---------|------------------|
| US1 | Problemas | `POST/GET /problemas`, `GET/PUT/DELETE /problemas/{id}`, `PATCH /problemas/{id}/desativar` |
| US2 | Estudantes | `POST/GET /estudantes`, `GET/PUT /estudantes/{id}` |
| US3 | Estratégias | `POST/GET /estrategias`, `GET/PUT /estrategias/{id}`, `PATCH /estrategias/{id}/prompt`, `GET /estrategias/tipos` |
| US4 | Turmas | `POST/GET /turmas`, `GET/PUT /turmas/{id}`, `PATCH /turmas/{id}/encerrar` |
| US5 | Turma x Estudante | `GET /turmas/{id}/estudantes`, `POST/DELETE /turmas/{id}/estudantes/{estudanteId}` |
| — | Atividades (mínimo) | `POST/GET /atividades`, `GET /atividades/{id}` |

Tipos de estratégia: `CODE_SMELLS`, `ACOPLAMENTO_COESAO_PRINCIPIOS`, `PADROES_DE_PROJETO`.

A entidade `Atividade` é mínima e existe para aplicar as regras das US1 e US4
(problema inativo / turma encerrada não recebem atividades; problema usado não pode ser removido).
