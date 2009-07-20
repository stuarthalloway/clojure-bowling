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

(deftest test-various-games
  (are [description expected-score game] (= expected-score (score game))
       "gutter game" 0 (gutter-game (new-game))
       "all ones" 20 (roll-many (new-game) 20 1)
       "one space" 16 (roll-many (roll-list (new-game) [5 5 3]) 17 0)
       "one strike" 24 (roll-many (roll-list (new-game) [10 3 4]) 16 0)
       "perfect game" 300 (roll-many (new-game) 12 10)
       ))



