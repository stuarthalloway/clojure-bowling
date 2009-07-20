(ns bowling-game)
(defn new-game [] [])

(defn next-two-balls [rolls]
  (+ (rolls 0) (rolls 1)))

(defn score-strike [rolls]
  [1 (+ 10 (+ (rolls 1) (rolls 2)))])

(defn score-spare [rolls]
  [2 (+ 10 (rolls 2))])

(defn score-no-mark [rolls]
  [2 (next-two-balls rolls)])

(defn score-next-frame [rolls]
  (if (= 10 (first rolls))
    (score-strike rolls)
    (if (= 10 (next-two-balls rolls))
      (score-spare rolls)
      (score-no-mark rolls))))

(defn score [game]
  (loop [frame 1 rolls game score 0]
    (if (> frame 10)
      score
      (let [frame-score (score-next-frame rolls)]
        (recur (inc frame) (subvec rolls (frame-score 0)) (+ score (frame-score 1)))))))

(defn roll [game pins]
  (conj game pins))
