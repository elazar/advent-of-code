#!/bin/sh
docker run -it -v `pwd`:/usr/src/app -w /usr/src/app clojure /bin/sh
