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

(defn take-until-seq
  ([s f] (take-until-seq s f []))
  ([s f c] 
    (let [nc (conj c (first s))]
      (if
        (f c)
        c
        (take-until-seq (rest s) f nc)
      )
    )
  )
)

(with-open [r (clojure.java.io/reader "input.txt")]
  (println
    (count
      (take-until-seq
        (map
          (fn [c] (if (= (compare (str c) "(") 0) 1 -1))
          (char-seq r)
        )
        (fn [c] (< (reduce + c) 0))
      )
    )
  )
)
