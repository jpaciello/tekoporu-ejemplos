tekoporu-ejemplos
=================

Ejemplos de uso de componentes del Framework Tekoporu

1) Ejemplo de aplicación que al arrancar ejecuta una tarea programada cada 10 segundos utilizando el componente tekoporu-scheduler con soporte CDI y managed persistence. Se verificó sobre JBoss 6.

Mirar los fuentes: 
- py.gov.setics.asistente.action.DemoScheduler
- web.xml (agregar el Listener para el tekoporu-scheduler)
- pom.xml (agregar la dependencia tekoporu-scheduler)
