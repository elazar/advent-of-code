(defn char-seq 
  [^java.io.Reader rdr]
  (let [chr (.read rdr)]
    (if (>= chr 0)
      (cons
        (char chr)
        (lazy-seq (char-seq rdr))
      )
    )
  )
)

(with-open [r (clojure.java.io/reader "input.txt")]
  (println
    (reduce +
      (map
        (fn [c] (case (str c) "(" 1 ")" -1 0))
        (char-seq r)
      )
    )
  )
)
