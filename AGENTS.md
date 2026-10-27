# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Evaluación de Competencia en Frameworks**.

| | |
|---|---|
| Tema | Experiencia en Frameworks |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | capas estándar |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.5.6
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-logging n/a
- org.postgresql:postgresql 42.7.3
- org.projectlombok:lombok 1.18.34
- org.springframework.boot:spring-boot-starter-test n/a
- org.springframework.boot:spring-boot-configuration-processor n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del Entorno**: Entorno de desarrollo configurado y listo para su uso.
- **Fase 2 — Enrutamiento de Solicitudes**: Sistema con rutas definidas y enrutamiento de solicitudes implementado.
- **Fase 3 — Generación de Logs**: Sistema con generación de logs implementada.
- **Fase 4 — Conexión con Base de Datos**: Sistema con conexión a la base de datos implementada.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (50)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/config/LoggingConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanmanagement/domain/Loan.java` — `LoanStatus.equals`
      Se invoca `equals` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setId`
      Se invoca `setId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setEndDate`
      Se invoca `setEndDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.getId`
      Se invoca `getId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.isEmpty`
      Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.get`
      Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.getStatus`
      Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.getStartDate`
      Se invoca `getStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.customerId`
      Se invoca `customerId` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.amount`
      Se invoca `amount` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.interestRate`
      Se invoca `interestRate` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.termMonths`
      Se invoca `termMonths` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.startDate`
      Se invoca `startDate` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `Loan.getId`
      Se invoca `getId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.status`
      Se invoca `status` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setId`
      Se invoca `setId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setEndDate`
      Se invoca `setEndDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.getId`
      Se invoca `getId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.getCustomerId`
      Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.getStatus`
      Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `LoanService.getLoansByCustomerId`
      Se invoca `getLoansByCustomerId` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.size`
      Se invoca `size` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.get`
      Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.isEmpty`
      Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setId`
      Se invoca `setId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setEndDate`
      Se invoca `setEndDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `LoanService.getLoansByCustomerId`
      Se invoca `getLoansByCustomerId` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (13)

- `pom.xml`
- `src/main/java/com/pragma/loanmanagement/LoanManagementApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/loanmanagement/domain/Loan.java`
- `src/main/java/com/pragma/loanmanagement/domain/LoanRepository.java`
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanJpaRepository.java`
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java`
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java`
- `src/main/java/com/pragma/loanmanagement/infrastructure/config/LoggingConfig.java`
- `src/main/java/com/pragma/loanmanagement/infrastructure/exception/LoanNotFoundException.java`
- `src/main/java/com/pragma/loanmanagement/infrastructure/exception/GlobalExceptionHandler.java`
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java`
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/loanmanagement`
- `src/main/java/com/pragma/loanmanagement/domain`
- `src/main/java/com/pragma/loanmanagement/application`
- `src/main/java/com/pragma/loanmanagement/infrastructure`
- `src/main/resources`
- `src/test/java/com/pragma/loanmanagement`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).
- Mision: Reto de evaluación de la competencia descrita.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
