(ns test
  (:use clojure.test))

(def tests
     ['test.bowling-game])

(doseq [test tests] (require test))

(apply run-tests tests)

(shutdown-agents)