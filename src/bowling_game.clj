(ns bowling-game)
(defn new-game [] [])

(defn next-two-balls [rolls]
  (apply + (take 2 rolls)))

(defn score-strike [rolls]
  (apply + (take 3 rolls)))

(defn score-spare [rolls]
  (apply + (take 3 rolls)))

(defn score-no-mark [rolls]
  (apply + (take 2 rolls)))

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn spare? [rolls]
  (= 10 (next-two-balls rolls)))

(defn score-next-frame [rolls]
  (cond
   (strike? rolls) (score-strike rolls)
   (spare? rolls) (score-spare rolls)
   :else (score-no-mark rolls)))

(defn frame-advance [rolls]
  (if (strike? rolls) 1 2))

(defn score-frames [frames]
  (letfn [(score-frames*
           [frames]
           (if (seq frames)
             (lazy-seq (cons (score-next-frame frames)
                             (score-frames* (drop (frame-advance frames) frames))))))]
    (take 10 (score-frames* frames))))
  
(defn score [frames]
  (apply + (score-frames frames)))    

(defn roll [game pins]
  (conj game pins))
