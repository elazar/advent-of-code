(deftype Dimensions [length width height])

(defn parse-dimensions
  [line]
  (let [[_, length, width, height] (re-find #"(\d+)x(\d+)x(\d+)" line)]
    (->Dimensions
      (read-string length)
      (read-string width)
      (read-string height)
    )
  )
)

(defmulti wrapping-paper class)
(defmethod wrapping-paper Dimensions [d]
  (let [
    l (.length d)
    w (.width d)
    h (.height d)
    [sx sy] (take 2 (sort [l w h]))
  ]
    (+ (* 2 l w) (* 2 w h) (* 2 h l) (* sx sy))
  )
)

(with-open [r (clojure.java.io/reader "input.txt")]
  (println
    (reduce +
      (map
        (comp wrapping-paper parse-dimensions)
        (line-seq r)
      )
    )
  )
)
