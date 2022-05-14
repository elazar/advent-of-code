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

(deftype Coordinates [x y])

(defmulti east class)
(defmethod east Coordinates [c] (->Coordinates (+ (.x c) 1) (.y c)))

(defmulti west class)
(defmethod west Coordinates [c] (->Coordinates (- (.x c) 1) (.y c)))

(defmulti north class)
(defmethod north Coordinates [c] (->Coordinates (.x c) (+ (.y c) 1)))

(defmulti south class)
(defmethod south Coordinates [c] (->Coordinates (.x c) (- (.y c) 1)))

(defmulti stay class)
(defmethod stay Coordinates [c] c)

(defmulti to-string class)
(defmethod to-string Coordinates [c] (print-str "(" (.x c) ", " (.y c) ")"))

(defn direction
  [d]
  (case (str d)
    ">" east
    "<" west
    "^" north
    "v" south
    stay
  )
)

(defn houses
  [start directions]
  (keys
    (group-by to-string
      (reduce
        #(conj %1 (%2 (last %1)))
        [start]
        (map direction directions)
      )
    )
  )
)

(with-open [r (clojure.java.io/reader "input.txt")]
  (def start (Coordinates. 0 0))
  (let [directions (char-seq r)]
    (println
      (count
        (set
          (concat
            (houses start (take-nth 2 directions))
            (houses start (take-nth 2 (rest directions)))
          )
        )
      )
    )
  )
)
