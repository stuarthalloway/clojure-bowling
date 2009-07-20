(ns test.bowling-game
  (:use clojure.test)
  (:use bowling-game))

; Tests could be improved by a macro that captures the descriptions,
; or by modifying 'are' to do so in some way.

(deftest scoring-frames
  (are [description score frames] (= score (score-next-frame frames))
       "strike" 30 [10 10 10]
       "spare" 20 [5 5 10]
       "no mark" 8 [5 3 5]))

(deftest advancing-frames
  (are [description advance frames] (= advance (frame-advance frames))
       "strike" 1 [10]
       "spare" 2 [5 5]
       "no mark" 2 [5 4]))

(deftest test-score-frames-for-various-games
  (are [description expected-scores game] (= expected-scores (score-frames game))
       "gutter game" (repeat 10 0) (repeat 0)
       "all ones" (repeat 10 2) (repeat 1)
       "all fives (spares)" (repeat 10 15) (repeat 5)
       "all tens (strikes)" (repeat 10 30) (repeat 10)
       ))

(deftest test-various-games
  (are [description expected-score game] (= expected-score (score game))
       "gutter game" 0 (repeat 0)
       "all ones" 20 (repeat 1)
       "one spare" 16 (concat [5 5 3] (repeat 0))
       "one strike" 24 (concat [10 3 4] (repeat 0))
       "perfect game" 300 (repeat 10)
       ))



