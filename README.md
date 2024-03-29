Giddyup Resources: RAD with Bootstrap [![Build Status](https://secure.travis-ci.org/asmala/giddyup-resources.png?branch=master)](http://travis-ci.org/asmala/giddyup-resources)
=====================================

A library for including [Bootstrap](http://getbootstrap.com/)
resources in the application pipeline. The main use case for the
library is early scaffolding for rapid application development,
together with [Giddyup](https://github.com/asmala/giddyup).


## Installation

Add the following to your `project.clj`:

```clojure
[giddyup-resources "0.3.0"]
```

For other options, please refer to the library
[Clojars page](https://clojars.org/giddyup-resources).


## Documentation

You can find examples below and for more extensive documentation you
can refer to the [API docs](http://asmala.github.com/giddyup-resources).


## Middleware

The middleware will make all Bootstrap CSS and JavaScript available
from the resource folder. It will also include jQuery so that no
other files are needed to get started.

```clojure
(def app-with-bootstrap
  (wrap-giddyup-resources app))
```


## JavaScript and CSS includes

```clojure
; Everything and the kitchen sink
(include-bootstrap)

; Or if you prefer granularity
(include-bootstrap-css)
(include-bootstrap-js)

; Responsive CSS can be excluded
(include-bootstrap {:responsive? false})
(include-boostrap-css {:responsive? false})

; You can also specify if you want the files minified or pulled from CDNJS
(include-bootstrap {:minified? true :location :cdn})
```


## Contributing

If you have suggestions for the library, you are welcome to open up a
[new issue](https://github.com/asmala/giddyup-resources/issues/new). I
also welcome code contributions, in which case I would recommend a
[pull request](https://help.github.com/articles/using-pull-requests)
with a feature branch.


## License

Copyright © 2012 Janne Asmala

Distributed under the
[Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html),
the same as Clojure.
