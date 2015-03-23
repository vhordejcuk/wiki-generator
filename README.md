# Wiki Generator

Backend used by my [personal Wiki](http://voho.cz/wiki/) to generate various images, etc.
This is not intended to be universal, so the outpout is customized to match my webpage.

## API

### GraphViz graphs

- *POST* http://host/gv/graph/
- *POST* http://host/gv/digraph/

request body: DOT source without the enclosing element (graph/digraph)

### PlantUML graphs

- *POST* http://host/uml/class/

request body: Plant UML source
