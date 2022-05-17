(defn re-contains
  [re s]
  (false? (nil? (re-find re s)))
)

(defn nice?
  [string]
  (and
    (re-contains #"(..).*(\1)" string)
    (re-contains #"(.).(\1)" string)
  )
)

(with-open [rdr (clojure.java.io/reader "input.txt")]
  (println (count (filter nice? (line-seq rdr))))
)
