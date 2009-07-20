(ns bowling-game
  (:use clojure.contrib.seq-utils))

(defn strike? [rolls]
  (= 10 (first rolls)))

(defn spare? [rolls]
  (= 10 (apply + (take 2 rolls))))

(defn balls-to-score
  "How many balls contribute to this frame's score?"
  [rolls]
  (cond
   (strike? rolls) 3
   (spare? rolls) 3
   :else 2))

(defn frame-advance
  "How many rolls should be consumed to advance to the next frame?"
  [rolls]
  (if (strike? rolls) 1 2))

(defn frames
  "Converts a sequence of rolls to a sequence of frames"
  [rolls]
  (when-let [rolls (seq rolls)]
    (lazy-seq (cons (take (balls-to-score rolls) rolls)
                    (frames (drop (frame-advance rolls) rolls))))))

(defn score-frame
  [frame]
  (reduce + frame))

(defn score-game
  "Score a bowling game, passed as a sequence of rolls."
  [rolls]
  (reduce + (map score-frame (take 10 (frames rolls)))))    

