# Wiki Generator

Backend used by my [personal Wiki](http://voho.cz/wiki/) to generate various figures. 
Powered by [Spring Boot](http://projects.spring.io/spring-boot/).
This is not intended to be universal, so the outpout is customized to match my webpage.

## GraphViz API [/gv/graph]

### POST

Generates an image from the source code received.

- request body: DOT source without the enclosing element (graph/digraph)
- response: PNG image

### DELETE

Deletes all cached images related to this resource.

## PlantUML class diagrams [/uml/class]

### POST

Generates an image from the source code received.

- request body: Plant UML source
- response: PNG image

### DELETE

Deletes all cached images related to this resource.
