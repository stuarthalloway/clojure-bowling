(ns test.bowling-game
  (:use clojure.test)
  (:use bowling-game))

(deftest can-create-game
  (is (not (nil? (new-game)))))

(deftest scoring-frames
  (are [description score frames] (= score (score-next-frame frames))
       :strike 30 [10 10 10]
       :spare 20 [5 5 10]
       :no-mark 8 [5 3 5]))

(deftest advancing-frames
  (is (= 1 (frame-advance [10])))
  (is (= 2 (frame-advance [5 5])))
  (is (= 2 (frame-advance [1 1]))))

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



