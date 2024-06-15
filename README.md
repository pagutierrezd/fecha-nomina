# Prueba técnica de Konecta en Java


### Librerías :
- lombok: [Documentación de Lombok](https://projectlombok.org/contributing/)
- jollyday: [Documentación de Jollyday](https://jollyday.sourceforge.net/)

## Estructura
## Raiz
```
├── FechaNomina
│ ├── .mvn
│ ├── src ├── main ├──java ├──com.example.fechaNomina
 
 ├── test
│ ├── .gitignore
│ ├── README.md
│ ├── mvnw
│ ├── mvnw.cmd
│ ├── pom.xml
```
## Instalación

Sigue estos pasos para configurar el proyecto localmente:

1. Clona el repositorio:

```bash
git clone https://github.com/pagutierrezd/fecha-nomina.git
```
2. Ejecutar proyecto:

```bash
* Abrimos intellij
* Esperamos que descarguen dependencias
* Ejecutamos el proyecto

* Abrimos postman: Documentación corta
  https://documenter.getpostman.com/view/27103331/2sA3XQhh62
```
## Requisito
```
Este proyecto tiene el siguiente endpoint:

/getPayrollDate: Este endpoint se utiliza para obtener la fecha de la nómina, según corresponda en el mes.
 Teniendo en cuenta que si el (15 o 30) es festivo, devolverá el día habil anterior al día.
```