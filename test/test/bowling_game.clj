(ns test.bowling-game
  (:use clojure.test)
  (:use bowling-game))

; Tests could be improved by a macro that captures the descriptions,
; or by modifying 'are' to do so in some way.

(deftest test-balls-to-score
  (are [description balls frames] (= balls (balls-to-score frames))
       "strike" 3 [10 10 10]
       "spare" 3 [5 5 10]
       "no mark" 2 [5 3 5]))

(deftest test-frame-advance
  (are [description advance frames] (= advance (frame-advance frames))
       "strike" 1 [10]
       "spare" 2 [5 5]
       "no mark" 2 [5 4]))

(deftest test-frames-for-various-games
  (are [description expected-frames game] (= expected-frames (take 10 (frames game)))
       "gutter game" (repeat 10 [0 0]) (repeat 0)
       "all ones" (repeat 10 [1 1]) (repeat 1)
       "all fives (spares)" (repeat 10 [5 5 5]) (repeat 5)
       "all tens (strikes)" (repeat 10 [10 10 10]) (repeat 10)
       ))

(deftest test-various-games
  (are [description expected-score game] (= expected-score (score game))
       "gutter game" 0 (repeat 0)
       "all ones" 20 (repeat 1)
       "one spare" 16 (concat [5 5 3] (repeat 0))
       "one strike" 24 (concat [10 3 4] (repeat 0))
       "perfect game" 300 (repeat 10)
       ))



