(let [input (slurp "input.txt")]
  (println
    (- (count (re-seq (re-pattern "\\(") input)) (count (re-seq (re-pattern "\\)") input)))
  )
)
