#!/bin/sh
docker run -it --rm -v `pwd`:/usr/src/app -w /usr/src/app clojure /bin/sh
