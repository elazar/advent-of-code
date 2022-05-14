(deftype Dimensions [length width height])

(defn parse-line
  [line]
  (let [[_, length, width, height] (re-find #"(\d+)x(\d+)x(\d+)" line)]
    (->Dimensions
      (read-string length)
      (read-string width)
      (read-string height)
    )
  )
)

(defmulti ribbon class)
(defmethod ribbon Dimensions [d]
  [d]
  (let [
    l (.length d)
    w (.width d)
    h (.height d)
    [sx sy] (take 2 (sort [l w h]))
  ]
    (+ (* 2 sx) (* 2 sy) (* l w h))
  )
)

(with-open [r (clojure.java.io/reader "input.txt")]
  (println
    (reduce +
      (map
        (comp ribbon parse-line)
        (line-seq r)
      )
    )
  )
)
