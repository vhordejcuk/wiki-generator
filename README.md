# Wiki Generator

Backend used by my [personal Wiki](http://voho.cz/wiki/) to generate various figures.
Just a bunch of simple servlets with some caching.

## GraphViz graphs

### GET /gv/graph?data={STRING}

- parameters
    - *data* = URL encoded source code (without *graph { }* envelope)
- response
    - response body is a PNG image

## GraphViz di-graphs

### GET /gv/digraph?data={STRING}

- parameters
    - *data* = URL encoded source code (without *digraph { }* envelope)
- response
    - response body is a PNG image

## PlantUML class diagrams

### GET /uml/class?data={STRING}

- parameters
    - *data* = URL encoded source code (without *@umlstart*)
- response
    - response body is a PNG image

## PlantUML activity diagrams

### GET /uml/activity?data={STRING}

- parameters
    - *data* = URL encoded source code (without *@umlstart*)
- response
    - response body is a PNG image

## PlantUML sequence diagrams

### GET /uml/sequence?data={STRING}

- parameters
    - *data* = URL encoded source code (without *@umlstart*)
- response
    - response body is a PNG image