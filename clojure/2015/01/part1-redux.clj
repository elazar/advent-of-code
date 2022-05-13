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

(defn apply-seq
  [s f]
  (if (not-empty s)
    (cons
      (apply f [(first s)])
      (lazy-seq (apply-seq (rest s) f))
    )
    ()
  )
)

(with-open [r (clojure.java.io/reader "input.txt")]
  (println
    (reduce +
      (apply-seq
        (char-seq r)
        ; The default case here shouldn't be necessary given the input, but the
        ; sequence returned by apply-seq includes a last element of nil and I
        ; haven't been able to figure out why yet.
        (fn [c] (case (str c) "(" 1 ")" -1 0))
      )
    )
  )
)
