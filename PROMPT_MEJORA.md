# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanmanagement/infrastructure/config/LoggingConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanmanagement/infrastructure/exception/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanmanagement/domain/Loan.java` — `LoanStatus.equals`: Se invoca `equals` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setId`: Se invoca `setId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setEndDate`: Se invoca `setEndDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.getId`: Se invoca `getId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.isEmpty`: Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.get`: Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.getStatus`: Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/application/LoanService.java` — `Loan.getStartDate`: Se invoca `getStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.customerId`: Se invoca `customerId` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.amount`: Se invoca `amount` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.interestRate`: Se invoca `interestRate` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.termMonths`: Se invoca `termMonths` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.startDate`: Se invoca `startDate` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `Loan.getId`: Se invoca `getId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java` — `UpdateLoanRequest.status`: Se invoca `status` sobre `UpdateLoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setId`: Se invoca `setId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setEndDate`: Se invoca `setEndDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.getId`: Se invoca `getId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.getCustomerId`: Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.getStatus`: Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `LoanService.getLoansByCustomerId`: Se invoca `getLoansByCustomerId` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.size`: Se invoca `size` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.get`: Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java` — `Loan.isEmpty`: Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setId`: Se invoca `setId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setEndDate`: Se invoca `setEndDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java` — `LoanService.getLoansByCustomerId`: Se invoca `getLoansByCustomerId` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
Reto de evaluación de la competencia descrita.

### Datos adicionales
El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Experiencia en Frameworks
- Seniority: advanced-l2
- Tipo: practical
- Título: Evaluación de Competencia en Frameworks
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno — objetivo: Configurar el entorno de desarrollo para utilizar un framework en tu lenguaje de programación principal. — entregable (NO resolver): Entorno de desarrollo configurado y listo para su uso.
- Fase 2: Enrutamiento de Solicitudes — objetivo: Implementar el enrutamiento de solicitudes utilizando el framework configurado. — entregable (NO resolver): Sistema con rutas definidas y enrutamiento de solicitudes implementado.
- Fase 3: Generación de Logs — objetivo: Implementar la generación de logs para el sistema utilizando el framework. — entregable (NO resolver): Sistema con generación de logs implementada.
- Fase 4: Conexión con Base de Datos — objetivo: Implementar la conexión con una base de datos utilizando un ORM. — entregable (NO resolver): Sistema con conexión a la base de datos implementada.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>loan-management</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loan-management</name>
    <description>Sistema de gestión de préstamos bancarios</description>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </dependency>
        
        <!-- Base de datos -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        
        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!-- Configuración -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/LoanManagementApplication.java ===
package com.pragma.loanmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@EnableScheduling
public class LoanManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LoanManagementApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        configureLogging();
    }
    
    private void configureLogging() {
        LoggingSystem.get(LoggingSystem.class.getClassLoader())
            .setLogLevel("org.hibernate.SQL", LogLevel.DEBUG);
        LoggingSystem.get(LoggingSystem.class.getClassLoader())
            .setLogLevel("com.pragma.loanmanagement", LogLevel.INFO);
    }
    
    @Bean
    public String demoInitialization() {
        // Inicialización de datos demo para pruebas locales
        return "Demo data initialization bean registered";
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loan-management
  
  datasource:
    url: jdbc:postgresql://localhost:5432/loan_db
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:postgres}
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
      pool-name: LoanHikariPool

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        jdbc:
          batch_size: 20
        order_inserts: true
        order_updates: true

server:
  port: 8080
  servlet:
    context-path: /api/loans
  error:
    include-message: always
    include-binding-errors: always
    include-stacktrace: on_param

logging:
  level:
    root: INFO
    com.pragma.loanmanagement: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,env,configprops
  endpoint:
    health:
      show-details: always

app:
  security:
    jwt:
      secret: ${JWT_SECRET:secret-key-change-in-production}
      expiration: 86400000
  scheduling:
    enabled: true
    thread-pool-size: 5

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/Loan.java ===
package com.pragma.loanmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private UUID id;

    @NotNull(message = "El cliente es obligatorio")
    private UUID customerId;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero")
    private BigDecimal amount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.01", message = "La tasa de interés debe ser mayor que cero")
    private BigDecimal interestRate;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    @NotNull(message = "La fecha de finalización es obligatoria")
    @Future(message = "La fecha de finalización debe ser futura")
    private LocalDate endDate;

    @NotNull(message = "El estado es obligatorio")
    private LoanStatus status;

    @Positive(message = "El plazo debe ser positivo")
    private int termMonths;

    public enum LoanStatus {
        PENDING, APPROVED, REJECTED, ACTIVE, PAID, DEFAULTED
    }

    public BigDecimal calculateTotalPayment() {
        BigDecimal monthlyInterest = interestRate.divide(BigDecimal.valueOf(12), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthlyPayment = amount.multiply(monthlyInterest)
                .divide(BigDecimal.ONE.subtract(BigDecimal.ONE.add(monthlyInterest).pow(-termMonths)), 10, BigDecimal.ROUND_HALF_UP);
        return monthlyPayment.multiply(BigDecimal.valueOf(termMonths));
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(endDate) && !status.equals(LoanStatus.PAID);
    }

    public BigDecimal calculateRemainingBalance() {
        if (status.equals(LoanStatus.PAID)) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalPaid = calculateTotalPaid();
        return calculateTotalPayment().subtract(totalPaid);
    }

    private BigDecimal calculateTotalPaid() {
        // En una implementación real, esto vendría de un registro de pagos
        // Para este ejemplo, simulamos un pago parcial
        BigDecimal totalPayment = calculateTotalPayment();
        BigDecimal paidPercentage = BigDecimal.valueOf(0.3); // 30% pagado
        return totalPayment.multiply(paidPercentage);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/domain/LoanRepository.java ===
package com.pragma.loanmanagement.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoanRepository {
    Loan save(Loan loan);
    Optional<Loan> findById(UUID id);
    List<Loan> findByCustomerId(UUID customerId);
    List<Loan> findByStatus(Loan.LoanStatus status);
    List<Loan> findAll();
    void deleteById(UUID id);
    List<Loan> findOverdueLoans(LocalDate currentDate);
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/infrastructure/LoanJpaRepository.java ===
package com.pragma.loanmanagement.infrastructure;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface LoanJpaRepository extends JpaRepository<LoanEntity, UUID>, LoanRepository {

    @Override
    default Loan save(Loan loan) {
        LoanEntity entity = LoanEntity.fromDomain(loan);
        LoanEntity savedEntity = save(entity);
        return savedEntity.toDomain();
    }

    @Override
    default Optional<Loan> findById(UUID id) {
        return findById(id).map(LoanEntity::toDomain);
    }

    @Override
    default List<Loan> findByCustomerId(UUID customerId) {
        return findByCustomerId(customerId).stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default List<Loan> findByStatus(Loan.LoanStatus status) {
        return findByStatus(status).stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default List<Loan> findAll() {
        return findAll().stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    default void deleteById(UUID id) {
        deleteById(id);
    }

    @Override
    default List<Loan> findOverdueLoans(LocalDate currentDate) {
        return findByEndDateBeforeAndStatusNot(currentDate, Loan.LoanStatus.PAID).stream()
                .map(LoanEntity::toDomain)
                .collect(Collectors.toList());
    }

    List<LoanEntity> findByCustomerId(UUID customerId);
    List<LoanEntity> findByStatus(Loan.LoanStatus status);
    List<LoanEntity> findByEndDateBeforeAndStatusNot(LocalDate endDate, Loan.LoanStatus status);
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/application/LoanService.java ===
package com.pragma.loanmanagement.application;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import com.pragma.loanmanagement.infrastructure.exception.LoanNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan createLoan(UUID customerId, BigDecimal amount, BigDecimal interestRate, 
                          int termMonths, LocalDate startDate) {
        logger.info("Iniciando creación de préstamo para cliente: {}", customerId);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Monto del préstamo inválido: {}", amount);
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }

        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) < 0) {
            logger.error("Tasa de interés inválida: {}", interestRate);
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }

        if (termMonths <= 0 || termMonths > 360) {
            logger.error("Plazo inválido: {} meses", termMonths);
            throw new IllegalArgumentException("El plazo del préstamo debe estar entre 1 y 360 meses");
        }

        if (startDate == null) {
            logger.error("Fecha de inicio no proporcionada");
            throw new IllegalArgumentException("La fecha de inicio del préstamo es obligatoria");
        }

        Loan loan = new Loan();
        loan.setId(UUID.randomUUID());
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMonths);
        loan.setStartDate(startDate);
        loan.setEndDate(startDate.plusMonths(termMonths));
        loan.setStatus(Loan.LoanStatus.ACTIVE);

        Loan savedLoan = loanRepository.save(loan);
        logger.info("Préstamo creado exitosamente con ID: {} para cliente: {}", 
                   savedLoan.getId(), customerId);

        return savedLoan;
    }

    public Loan getLoanById(UUID loanId) {
        logger.info("Buscando préstamo con ID: {}", loanId);

        Optional<Loan> loan = loanRepository.findById(loanId);
        if (loan.isEmpty()) {
            logger.warn("Préstamo no encontrado con ID: {}", loanId);
            throw new LoanNotFoundException("Préstamo no encontrado con ID: " + loanId);
        }

        logger.info("Préstamo encontrado: {}", loan.get().getId());
        return loan.get();
    }

    public List<Loan> getLoansByCustomer(UUID customerId) {
        logger.info("Buscando préstamos para cliente: {}", customerId);

        List<Loan> loans = loanRepository.findByCustomerId(customerId);
        logger.info("Se encontraron {} préstamos para el cliente: {}", loans.size(), customerId);

        return loans;
    }

    public List<Loan> getLoansByStatus(Loan.LoanStatus status) {
        logger.info("Buscando préstamos con estado: {}", status);

        List<Loan> loans = loanRepository.findByStatus(status);
        logger.info("Se encontraron {} préstamos con estado: {}", loans.size(), status);

        return loans;
    }

    public List<Loan> getAllLoans() {
        logger.info("Obteniendo todos los préstamos del sistema");

        List<Loan> loans = loanRepository.findAll();
        logger.info("Total de préstamos en el sistema: {}", loans.size());

        return loans;
    }

    public Loan updateLoanStatus(UUID loanId, Loan.LoanStatus newStatus) {
        logger.info("Actualizando estado del préstamo {} a {}", loanId, newStatus);

        Loan loan = getLoanById(loanId);
        Loan.LoanStatus oldStatus = loan.getStatus();
        loan.setStatus(newStatus);

        Loan updatedLoan = loanRepository.save(loan);
        logger.info("Estado del préstamo {} actualizado de {} a {}", 
                   loanId, oldStatus, newStatus);

        return updatedLoan;
    }

    public Loan updateLoan(UUID loanId, BigDecimal newAmount, BigDecimal newInterestRate, 
                          Integer newTermMonths) {
        logger.info("Actualizando préstamo con ID: {}", loanId);

        Loan loan = getLoanById(loanId);

        if (newAmount != null && newAmount.compareTo(BigDecimal.ZERO) > 0) {
            loan.setAmount(newAmount);
            logger.debug("Monto actualizado a: {}", newAmount);
        }

        if (newInterestRate != null && newInterestRate.compareTo(BigDecimal.ZERO) >= 0) {
            loan.setInterestRate(newInterestRate);
            logger.debug("Tasa de interés actualizada a: {}", newInterestRate);
        }

        if (newTermMonths != null && newTermMonths > 0 && newTermMonths <= 360) {
            loan.setTermMonths(newTermMonths);
            loan.setEndDate(loan.getStartDate().plusMonths(newTermMonths));
            logger.debug("Plazo actualizado a {} meses", newTermMonths);
        }

        Loan updatedLoan = loanRepository.save(loan);
        logger.info("Préstamo {} actualizado exitosamente", loanId);

        return updatedLoan;
    }

    public void deleteLoan(UUID loanId) {
        logger.info("Eliminando préstamo con ID: {}", loanId);

        Loan loan = getLoanById(loanId);
        loanRepository.deleteById(loanId);

        logger.info("Préstamo {} eliminado exitosamente", loanId);
    }

    public List<Loan> getOverdueLoans() {
        logger.info("Buscando préstamos vencidos");

        LocalDate currentDate = LocalDate.now();
        List<Loan> overdueLoans = loanRepository.findOverdueLoans(currentDate);

        logger.info("Se encontraron {} préstamos vencidos", overdueLoans.size());
        return overdueLoans;
    }

    public BigDecimal calculateTotalPayment(UUID loanId) {
        logger.info("Calculando pago total para préstamo: {}", loanId);

        Loan loan = getLoanById(loanId);
        BigDecimal totalPayment = loan.calculateTotalPayment();

        logger.info("Pago total calculado para préstamo {}: {}", loanId, totalPayment);
        return totalPayment;
    }

    public BigDecimal calculateRemainingBalance(UUID loanId) {
        logger.info("Calculando saldo pendiente para préstamo: {}", loanId);

        Loan loan = getLoanById(loanId);
        BigDecimal remainingBalance = loan.calculateRemainingBalance();

        logger.info("Saldo pendiente para préstamo {}: {}", loanId, remainingBalance);
        return remainingBalance;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/infrastructure/LoanController.java ===
package com.pragma.loanmanagement.infrastructure;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.application.LoanService;
import com.pragma.loanmanagement.domain.Loan;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody CreateLoanRequest request) {
        logger.info("Recibida solicitud para crear préstamo - Cliente: {}, Monto: {}", 
                   request.customerId(), request.amount());

        try {
            Loan loan = loanService.createLoan(
                request.customerId(),
                request.amount(),
                request.interestRate(),
                request.termMonths(),
                request.startDate()
            );

            logger.info("Préstamo creado exitosamente - ID: {}", loan.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(loan);
        } catch (IllegalArgumentException e) {
            logger.error("Error al crear préstamo: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoan(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para obtener préstamo: {}", loanId);

        try {
            Loan loan = loanService.getLoanById(loanId);
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            logger.error("Error al obtener préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomer(@PathVariable UUID customerId) {
        logger.info("Recibida solicitud para obtener préstamos del cliente: {}", customerId);

        List<Loan> loans = loanService.getLoansByCustomer(customerId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Loan>> getLoansByStatus(@PathVariable Loan.LoanStatus status) {
        logger.info("Recibida solicitud para obtener préstamos con estado: {}", status);

        List<Loan> loans = loanService.getLoansByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        logger.info("Recibida solicitud para obtener todos los préstamos");

        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/{loanId}/status")
    public ResponseEntity<Loan> updateLoanStatus(
            @PathVariable UUID loanId,
            @RequestBody UpdateStatusRequest request) {
        logger.info("Recibida solicitud para actualizar estado del préstamo {} a {}", 
                   loanId, request.status());

        try {
            Loan loan = loanService.updateLoanStatus(loanId, request.status());
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            logger.error("Error al actualizar estado del préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoan(
            @PathVariable UUID loanId,
            @RequestBody UpdateLoanRequest request) {
        logger.info("Recibida solicitud para actualizar préstamo: {}", loanId);

        try {
            Loan loan = loanService.updateLoan(
                loanId,
                request.amount(),
                request.interestRate(),
                request.termMonths()
            );
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            logger.error("Error al actualizar préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para eliminar préstamo: {}", loanId);

        try {
            loanService.deleteLoan(loanId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar préstamo {}: {}", loanId, e.getMessage());
            throw e;
        }
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Loan>> getOverdueLoans() {
        logger.info("Recibida solicitud para obtener préstamos vencidos");

        List<Loan> loans = loanService.getOverdueLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{loanId}/total-payment")
    public ResponseEntity<BigDecimal> calculateTotalPayment(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para calcular pago total del préstamo: {}", loanId);

        BigDecimal totalPayment = loanService.calculateTotalPayment(loanId);
        return ResponseEntity.ok(totalPayment);
    }

    @GetMapping("/{loanId}/remaining-balance")
    public ResponseEntity<BigDecimal> calculateRemainingBalance(@PathVariable UUID loanId) {
        logger.info("Recibida solicitud para calcular saldo pendiente del préstamo: {}", loanId);

        BigDecimal remainingBalance = loanService.calculateRemainingBalance(loanId);
        return ResponseEntity.ok(remainingBalance);
    }

    public record CreateLoanRequest(
        @NotNull UUID customerId,
        @NotNull BigDecimal amount,
        @NotNull BigDecimal interestRate,
        @NotNull Integer termMonths,
        @NotNull LocalDate startDate
    ) {}

    public record UpdateLoanRequest(
        BigDecimal amount,
        BigDecimal interestRate,
        Integer termMonths
    ) {}

    public record UpdateStatusRequest(
        @NotNull Loan.LoanStatus status
    ) {}
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/infrastructure/config/LoggingConfig.java ===
package com.pragma.loanmanagement.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_ATTRIBUTE = "requestId";

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/actuator/**");
    }

    public static class LoggingInterceptor implements org.springframework.web.servlet.HandlerInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String requestId = request.getHeader(REQUEST_ID_HEADER);
            if (requestId == null || requestId.isBlank()) {
                requestId = UUID.randomUUID().toString();
            }

            request.setAttribute(REQUEST_ID_ATTRIBUTE, requestId);

            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);

            logger.info("[{}] {} {} - Inicio de solicitud - IP: {}",
                    requestId,
                    request.getMethod(),
                    request.getRequestURI(),
                    getClientIpAddress(request));

            return true;
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                                   Object handler, Exception ex) {
            String requestId = (String) request.getAttribute(REQUEST_ID_ATTRIBUTE);
            Long startTime = (Long) request.getAttribute("startTime");
            long duration = startTime != null ? System.currentTimeMillis() - startTime : 0;

            int httpStatus = response.getStatus();
            String logLevel = determineLogLevel(httpStatus);

            if (logger.isInfoEnabled()) {
                logger.info("[{}] {} {} - Completado - Estado: {} - Duración: {}ms",
                        requestId,
                        request.getMethod(),
                        request.getRequestURI(),
                        httpStatus,
                        duration);
            }

            if (ex != null && logger.isErrorEnabled()) {
                logger.error("[{}] {} {} - Error: {}",
                        requestId,
                        request.getMethod(),
                        request.getRequestURI(),
                        ex.getMessage(),
                        ex);
            }
        }

        private String determineLogLevel(int httpStatus) {
            if (httpStatus >= 500) {
                return "ERROR";
            } else if (httpStatus >= 400) {
                return "WARN";
            } else {
                return "INFO";
            }
        }

        private String getClientIpAddress(HttpServletRequest request) {
            String xForwardedFor = request.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isBlank()) {
                return xForwardedFor.split(",")[0].trim();
            }

            String xRealIp = request.getHeader("X-Real-IP");
            if (xRealIp != null && !xRealIp.isBlank()) {
                return xRealIp;
            }

            return request.getRemoteAddr();
        }
    }

    public static void logInfo(String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.info(message, args);
    }

    public static void logWarn(String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.warn(message, args);
    }

    public static void logError(String message, Throwable throwable) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.error(message, throwable);
    }

    public static void logDebug(String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.debug(message, args);
    }

    public static void configureLogLevels(String packageName, String level) {
        Logger logger = LoggerFactory.getLogger(packageName);
        if ("DEBUG".equalsIgnoreCase(level)) {
            logger.debug("Nivel de logging configurado para {}: {}", packageName, level);
        } else if ("INFO".equalsIgnoreCase(level)) {
            logger.info("Nivel de logging configurado para {}: {}", packageName, level);
        } else if ("WARN".equalsIgnoreCase(level)) {
            logger.warn("Nivel de logging configurado para {}: {}", packageName, level);
        } else if ("ERROR".equalsIgnoreCase(level)) {
            logger.error("Nivel de logging configurado para {}: {}", packageName, level);
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/infrastructure/exception/LoanNotFoundException.java ===
package com.pragma.loanmanagement.infrastructure.exception;

import java.util.UUID;

public class LoanNotFoundException extends RuntimeException {
    private final UUID loanId;
    private final String errorCode;
    private final String category;
    private final long timestamp;

    public LoanNotFoundException(UUID loanId) {
        super("Préstamo no encontrado con ID: " + loanId);
        if (loanId == null) {
            throw new IllegalArgumentException("El ID del préstamo no puede ser null");
        }
        this.loanId = loanId;
        this.errorCode = "LOAN_NOT_FOUND";
        this.category = "RESOURCE_NOT_FOUND";
        this.timestamp = System.currentTimeMillis();
    }

    public LoanNotFoundException(UUID loanId, String message) {
        super(message);
        if (loanId == null) {
            throw new IllegalArgumentException("El ID del préstamo no puede ser null");
        }
        this.loanId = loanId;
        this.errorCode = "LOAN_NOT_FOUND";
        this.category = "RESOURCE_NOT_FOUND";
        this.timestamp = System.currentTimeMillis();
    }

    public UUID getLoanId() {
        return loanId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getCategory() {
        return category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDetailedMessage() {
        return String.format("[%s] Préstamo no encontrado: ID=%s, Categoría=%s, Código=%s",
                timestamp, loanId, category, errorCode);
    }

    public boolean hasLoanId() {
        return this.loanId != null;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanmanagement/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.loanmanagement.infrastructure.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFoundException(
            LoanNotFoundException ex, WebRequest request) {
        logger.warn("Préstamo no encontrado: {} - Path: {}", 
                ex.getLoanId(), 
                request.getDescription(false));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .path(request.getDescription(false).replace("uri=", ""))
                .loanId(ex.getLoanId() != null ? ex.getLoanId().toString() : null)
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación en solicitud: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false));
        
        List<FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldError(
                        error.getField(),
                        error.getDefaultMessage(),
                        error.getRejectedValue() != null ? error.getRejectedValue().toString() : null))
                .collect(Collectors.toList());
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Error en la validación de campos")
                .fieldErrors(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        logger.warn("Tipo de argumento inválido: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false));
        
        String message = String.format("El parámetro '%s' tiene un valor inválido: '%s'",
                ex.getName(), ex.getValue());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .errorCode("TYPE_MISMATCH")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .errorCode("ILLEGAL_ARGUMENT")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {} - Path: {}", 
                ex.getMessage(), 
                request.getDescription(false), 
                ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(formatter))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .errorCode("INTERNAL_ERROR")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse {
        private String timestamp;
        private int status;
        private String error;
        private String message;
        private String errorCode;
        private String path;
        private String loanId;
        private Map<String, Object> additionalInfo;

        private ErrorResponse() {}

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getErrorCode() { return errorCode; }
        public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getLoanId() { return loanId; }
        public void setLoanId(String loanId) { this.loanId = loanId; }
        public Map<String, Object> getAdditionalInfo() { return additionalInfo; }
        public void setAdditionalInfo(Map<String, Object> additionalInfo) { this.additionalInfo = additionalInfo; }

        public static class ErrorResponseBuilder {
            private String timestamp;
            private int status;
            private String error;
            private String message;
            private String errorCode;
            private String path;
            private String loanId;
            private Map<String, Object> additionalInfo = new HashMap<>();

            public ErrorResponseBuilder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
            public ErrorResponseBuilder status(int status) { this.status = status; return this; }
            public ErrorResponseBuilder error(String error) { this.error = error; return this; }
            public ErrorResponseBuilder message(String message) { this.message = message; return this; }
            public ErrorResponseBuilder errorCode(String errorCode) { this.errorCode = errorCode; return this; }
            public ErrorResponseBuilder path(String path) { this.path = path; return this; }
            public ErrorResponseBuilder loanId(String loanId) { this.loanId = loanId; return this; }
            public ErrorResponseBuilder additionalInfo(String key, Object value) { this.additionalInfo.put(key, value); return this; }
            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.errorCode = this.errorCode;
                response.path = this.path;
                response.loanId = this.loanId;
                response.additionalInfo = this.additionalInfo;
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private String timestamp;
        private int status;
        private String error;
        private String message;
        private List<FieldError> fieldErrors;
        private String path;

        private ValidationErrorResponse() {}

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public List<FieldError> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(List<FieldError> fieldErrors) { this.fieldErrors = fieldErrors; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ValidationErrorResponseBuilder {
            private String timestamp;
            private int status;
            private String error;
            private String message;
            private List<FieldError> fieldErrors;
            private String path;

            public ValidationErrorResponseBuilder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
            public ValidationErrorResponseBuilder status(int status) { this.status = status; return this; }
            public ValidationErrorResponseBuilder error(String error) { this.error = error; return this; }
            public ValidationErrorResponseBuilder message(String message) { this.message = message; return this; }
            public ValidationErrorResponseBuilder fieldErrors(List<FieldError> fieldErrors) { this.fieldErrors = fieldErrors; return this; }
            public ValidationErrorResponseBuilder path(String path) { this.path = path; return this; }
            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.fieldErrors = this.fieldErrors;
                response.path = this.path;
                return response;
            }
        }
    }

    public static class FieldError {
        private String field;
        private String message;
        private String rejectedValue;

        public FieldError(String field, String message, String rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(String rejectedValue) { this.rejectedValue = rejectedValue; }
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanmanagement/application/LoanServiceTest.java ===
package com.pragma.loanmanagement.application;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.domain.LoanRepository;
import com.pragma.loanmanagement.infrastructure.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Loan sampleLoan;
    private UUID sampleCustomerId;
    private UUID sampleLoanId;

    @BeforeEach
    void setUp() {
        sampleCustomerId = UUID.randomUUID();
        sampleLoanId = UUID.randomUUID();
        sampleLoan = new Loan();
        sampleLoan.setId(sampleLoanId);
        sampleLoan.setCustomerId(sampleCustomerId);
        sampleLoan.setAmount(new BigDecimal("10000.00"));
        sampleLoan.setInterestRate(new BigDecimal("0.15"));
        sampleLoan.setTermMonths(12);
        sampleLoan.setStartDate(LocalDate.now());
        sampleLoan.setEndDate(LocalDate.now().plusMonths(12));
        sampleLoan.setStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void createLoan_Success_ReturnsCreatedLoan() {
        when(loanRepository.save(any(Loan.class))).thenReturn(sampleLoan);

        Loan result = loanService.createLoan(sampleLoan);

        assertNotNull(result);
        assertEquals(sampleLoanId, result.getId());
        assertEquals(sampleCustomerId, result.getCustomerId());
        assertEquals(Loan.LoanStatus.ACTIVE, result.getStatus());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void getLoanById_ExistingLoan_ReturnsLoan() {
        when(loanRepository.findById(sampleLoanId)).thenReturn(Optional.of(sampleLoan));

        Loan result = loanService.getLoanById(sampleLoanId);

        assertNotNull(result);
        assertEquals(sampleLoanId, result.getId());
        verify(loanRepository, times(1)).findById(sampleLoanId);
    }

    @Test
    void getLoanById_NonExistingLoan_ThrowsException() {
        UUID nonExistingId = UUID.randomUUID();
        when(loanRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(LoanNotFoundException.class, () -> loanService.getLoanById(nonExistingId));
        verify(loanRepository, times(1)).findById(nonExistingId);
    }

    @Test
    void getLoansByCustomerId_ExistingCustomer_ReturnsLoanList() {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanRepository.findByCustomerId(sampleCustomerId)).thenReturn(loans);

        List<Loan> result = loanService.getLoansByCustomerId(sampleCustomerId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleCustomerId, result.get(0).getCustomerId());
        verify(loanRepository, times(1)).findByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByCustomerId_NoLoans_ReturnsEmptyList() {
        when(loanRepository.findByCustomerId(sampleCustomerId)).thenReturn(new ArrayList<>());

        List<Loan> result = loanService.getLoansByCustomerId(sampleCustomerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(loanRepository, times(1)).findByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByStatus_ActiveStatus_ReturnsLoanList() {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanRepository.findByStatus(Loan.LoanStatus.ACTIVE)).thenReturn(loans);

        List<Loan> result = loanService.getLoansByStatus(Loan.LoanStatus.ACTIVE);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Loan.LoanStatus.ACTIVE, result.get(0).getStatus());
        verify(loanRepository, times(1)).findByStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void updateLoanStatus_ExistingLoan_UpdatesStatus() {
        when(loanRepository.findById(sampleLoanId)).thenReturn(Optional.of(sampleLoan));
        when(loanRepository.save(any(Loan.class))).thenReturn(sampleLoan);

        Loan result = loanService.updateLoanStatus(sampleLoanId, Loan.LoanStatus.PAID);

        assertNotNull(result);
        verify(loanRepository, times(1)).findById(sampleLoanId);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void deleteLoan_ExistingLoan_DeletesSuccessfully() {
        doNothing().when(loanRepository).deleteById(sampleLoanId);

        loanService.deleteLoan(sampleLoanId);

        verify(loanRepository, times(1)).deleteById(sampleLoanId);
    }

    @Test
    void getAllLoans_ReturnsAllLoans() {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanService.getAllLoans();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void calculateTotalPayment_ReturnsCorrectAmount() {
        BigDecimal totalPayment = sampleLoan.calculateTotalPayment();

        assertNotNull(totalPayment);
        assertTrue(totalPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void isOverdue_OverdueLoan_ReturnsTrue() {
        sampleLoan.setEndDate(LocalDate.now().minusDays(1));

        boolean isOverdue = sampleLoan.isOverdue();

        assertTrue(isOverdue);
    }

    @Test
    void isOverdue_ActiveLoan_ReturnsFalse() {
        sampleLoan.setEndDate(LocalDate.now().plusDays(30));

        boolean isOverdue = sampleLoan.isOverdue();

        assertFalse(isOverdue);
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanmanagement/infrastructure/LoanControllerTest.java ===
package com.pragma.loanmanagement.infrastructure;


import com.pragma.loanmanagement.domain.LoanStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanmanagement.application.LoanService;
import com.pragma.loanmanagement.domain.Loan;
import com.pragma.loanmanagement.infrastructure.exception.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanService loanService;

    private Loan sampleLoan;
    private UUID sampleCustomerId;
    private UUID sampleLoanId;

    @BeforeEach
    void setUp() {
        sampleCustomerId = UUID.randomUUID();
        sampleLoanId = UUID.randomUUID();
        sampleLoan = new Loan();
        sampleLoan.setId(sampleLoanId);
        sampleLoan.setCustomerId(sampleCustomerId);
        sampleLoan.setAmount(new BigDecimal("10000.00"));
        sampleLoan.setInterestRate(new BigDecimal("0.15"));
        sampleLoan.setTermMonths(12);
        sampleLoan.setStartDate(LocalDate.now());
        sampleLoan.setEndDate(LocalDate.now().plusMonths(12));
        sampleLoan.setStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void createLoan_ValidRequest_ReturnsCreatedStatus() throws Exception {
        when(loanService.createLoan(any(Loan.class))).thenReturn(sampleLoan);

        mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleLoan)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sampleLoanId.toString()))
                .andExpect(jsonPath("$.customerId").value(sampleCustomerId.toString()))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(loanService, times(1)).createLoan(any(Loan.class));
    }

    @Test
    void getLoanById_ExistingLoan_ReturnsOkStatus() throws Exception {
        when(loanService.getLoanById(sampleLoanId)).thenReturn(sampleLoan);

        mockMvc.perform(get("/api/loans/{id}", sampleLoanId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleLoanId.toString()))
                .andExpect(jsonPath("$.customerId").value(sampleCustomerId.toString()));

        verify(loanService, times(1)).getLoanById(sampleLoanId);
    }

    @Test
    void getLoanById_NonExistingLoan_ReturnsNotFoundStatus() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        when(loanService.getLoanById(nonExistingId)).thenThrow(new LoanNotFoundException("Loan not found"));

        mockMvc.perform(get("/api/loans/{id}", nonExistingId))
                .andExpect(status().isNotFound());

        verify(loanService, times(1)).getLoanById(nonExistingId);
    }

    @Test
    void getLoansByCustomerId_ExistingCustomer_ReturnsOkStatus() throws Exception {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanService.getLoansByCustomerId(sampleCustomerId)).thenReturn(loans);

        mockMvc.perform(get("/api/loans/customer/{customerId}", sampleCustomerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerId").value(sampleCustomerId.toString()));

        verify(loanService, times(1)).getLoansByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByCustomerId_NoLoans_ReturnsEmptyList() throws Exception {
        when(loanService.getLoansByCustomerId(sampleCustomerId)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/loans/customer/{customerId}", sampleCustomerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(loanService, times(1)).getLoansByCustomerId(sampleCustomerId);
    }

    @Test
    void getLoansByStatus_ValidStatus_ReturnsOkStatus() throws Exception {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanService.getLoansByStatus(Loan.LoanStatus.ACTIVE)).thenReturn(loans);

        mockMvc.perform(get("/api/loans/status/{status}", "ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].status").value("ACTIVE"));

        verify(loanService, times(1)).getLoansByStatus(Loan.LoanStatus.ACTIVE);
    }

    @Test
    void getAllLoans_ReturnsOkStatus() throws Exception {
        List<Loan> loans = new ArrayList<>();
        loans.add(sampleLoan);
        when(loanService.getAllLoans()).thenReturn(loans);

        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(loanService, times(1)).getAllLoans();
    }

    @Test
    void updateLoanStatus_ValidRequest_ReturnsOkStatus() throws Exception {
        when(loanService.updateLoanStatus(sampleLoanId, Loan.LoanStatus.PAID)).thenReturn(sampleLoan);

        mockMvc.perform(put("/api/loans/{id}/status", sampleLoanId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"PAID\""))
                .andExpect(status().isOk());

        verify(loanService, times(1)).updateLoanStatus(sampleLoanId, Loan.LoanStatus.PAID);
    }

    @Test
    void deleteLoan_ExistingLoan_ReturnsNoContentStatus() throws Exception {
        doNothing().when(loanService).deleteLoan(sampleLoanId);

        mockMvc.perform(delete("/api/loans/{id}", sampleLoanId))
                .andExpect(status().isNoContent());

        verify(loanService, times(1)).deleteLoan(sampleLoanId);
    }

    @Test
    void createLoan_InvalidRequest_ReturnsBadRequestStatus() throws Exception {
        Loan invalidLoan = new Loan();
        invalidLoan.setId(sampleLoanId);

        mockMvc.perform(post("/api/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidLoan)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getLoanById_InvalidUuid_ReturnsBadRequestStatus() throws Exception {
        mockMvc.perform(get("/api/loans/{id}", "invalid-uuid"))
                .andExpect(status().isBadRequest());
    }
}
```
