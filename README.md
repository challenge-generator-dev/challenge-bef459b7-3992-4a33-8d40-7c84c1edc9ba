# Evaluación de Competencia en Frameworks

El objetivo de este reto es evaluar tu competencia en el uso de frameworks en tu lenguaje de programación principal. Debes demostrar tu habilidad para enrutar solicitudes, controlar variables de entorno, generar logs y conectarte con bases de datos utilizando un ORM. El dominio es banca, donde un sistema de gestión de préstamos necesita integrarse con un framework para manejar estas funcionalidades.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Experiencia en Frameworks |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno

**Objetivo:** Configurar el entorno de desarrollo para utilizar un framework en tu lenguaje de programación principal.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar y configurar el framework apropiado para el lenguaje de programación principal.
- Asegurar que el entorno de desarrollo esté preparado para enrutar solicitudes y manejar variables de entorno.

**Entregable:** Entorno de desarrollo configurado y listo para su uso.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la configuración del entorno en el contexto de un framework.
- Piensa en cómo las variables de entorno pueden afectar el comportamiento del sistema.

</details>

### Fase 2: Enrutamiento de Solicitudes

**Objetivo:** Implementar el enrutamiento de solicitudes utilizando el framework configurado.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Definir las rutas necesarias para manejar las solicitudes del sistema de gestión de préstamos.
- Asegurar que el enrutamiento sea eficiente y siga las mejores prácticas del framework elegido.

**Entregable:** Sistema con rutas definidas y enrutamiento de solicitudes implementado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la estructura de las URL y cómo afectan al enrutamiento.
- Piensa en cómo manejarías los errores de enrutamiento.

</details>

### Fase 3: Generación de Logs

**Objetivo:** Implementar la generación de logs para el sistema utilizando el framework.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el sistema para generar logs de las operaciones realizadas.
- Asegurar que los logs sean útiles para la depuración y el monitoreo del sistema.

**Entregable:** Sistema con generación de logs implementada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los niveles de log y cómo afectan a la información registrada.
- Piensa en cómo almacenarías y gestionarías los logs.

</details>

### Fase 4: Conexión con Base de Datos

**Objetivo:** Implementar la conexión con una base de datos utilizando un ORM.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar la conexión con la base de datos utilizando un ORM.
- Asegurar que la conexión sea segura y eficiente.

**Entregable:** Sistema con conexión a la base de datos implementada.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la seguridad de la conexión.
- Piensa en cómo manejarías los errores de conexión.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un framework y por qué es importante en el desarrollo de aplicaciones?
- **paraQueSirve**: ¿Para qué sirve el enrutamiento de solicitudes en un framework?
- **comoSeUsa**: ¿Cómo se usa un ORM para conectarse con una base de datos?
- **erroresComunes**: ¿Cuáles son los errores comunes al configurar un entorno de desarrollo y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la elección de un framework para un proyecto?

## Criterios de Evaluacion

- Configuración correcta del entorno de desarrollo.
- Implementación eficiente del enrutamiento de solicitudes.
- Generación útil y segura de logs.
- Conexión segura y eficiente con la base de datos utilizando un ORM.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
