(import 'java.security.MessageDigest
        'java.math.BigInteger)

(defn md5 [^String s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        raw (.digest algorithm (.getBytes s))]
    (format "%032x" (BigInteger. 1 raw))))

(defn not-match?
  [hash]
  (nil?
    (re-find #"^0{5}" hash)
  )
)

(defn find-match
  [input]
  (inc
    (count
      (take-while
        not-match?
        (map
          (fn [n] (md5 (str input n)))
          (map inc (range))
        )
      )
    )
  )
)

(let [input "bgvyzdsv"]
  (println (find-match input))
)
