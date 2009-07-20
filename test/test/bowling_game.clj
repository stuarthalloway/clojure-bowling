(ns test.bowling-game
  (:use clojure.test)
  (:use bowling-game))

(defn roll-list [game list]
  (if (empty? list)
    game
    (roll-list (roll game (first list)) (rest list))
    ))

(defn roll-many [game n pins]
  (loop [i n g game]
    (if (zero? i)
      g
      (recur (dec i) (roll g pins)))))

(defn gutter-game [game] (roll-many game 20 0))

(deftest can-create-game
  (is (not (nil? (new-game)))))

(deftest gutter-game-should-score-0
  (is (= 0 (score (gutter-game (new-game))))))

(deftest all-ones-should-score-20
  (is (= 20 (score (roll-many (new-game) 20 1)))))

(deftest one-spare
  (is (= 16 (score
    (roll-many
      (roll-list (new-game) [5 5 3])
      17 0)))))

(deftest one_strike
  (is (= 24 (score
    (roll-many
      (roll-list (new-game) [10 3 4])
      16 0)))))

(deftest perfect-game
  (is (= 300 (score (roll-many (new-game) 12 10)))))


