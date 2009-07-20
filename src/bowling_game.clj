(ns bowling-game)

(defn score-strike [rolls]
  (apply + (take 3 rolls)))

(defn score-spare [rolls]
  (apply + (take 3 rolls)))

(defn score-no-mark [rolls]
  (apply + (take 2 rolls)))

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn spare? [rolls]
  (= 10 (apply + (take 2 rolls))))

(defn score-next-frame
  "Score the next frame from a list of rolls."
  [rolls]
  (cond
   (strike? rolls) (score-strike rolls)
   (spare? rolls) (score-spare rolls)
   :else (score-no-mark rolls)))

(defn frame-advance
  "How many rolls should be consumed to advance to the next frame?"
  [rolls]
  (if (strike? rolls) 1 2))

(defn score-frames
  "Lazy sequence of scores for up to a game's worth of rolls."
  [rolls]
  (letfn [(score-rolls*
           [rolls]
           (if (seq rolls)
             (lazy-seq (cons (score-next-frame rolls)
                             (score-rolls* (drop (frame-advance rolls) rolls))))))]
    (take 10 (score-rolls* rolls))))
  
(defn score
  "Score a bowling game, passed as a sequence of rolls."
  [rolls]
  (apply + (score-frames rolls)))    

(defn roll [game pins]
  (conj game pins))
