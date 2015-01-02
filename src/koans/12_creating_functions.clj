(ns koans.12-creating-functions
  (:require [koan-engine.core :refer :all]))

(defn square [x] (* x x))

(meditations
  "One may know what they seek by knowing what they do not seek"
  (= [true false true] (let [not-a-symbol? (complement symbol?)]
                  (map not-a-symbol? [:a 'b "c"])))

  "Praise and 'complement' may help you separate the wheat from the chaff"
  (= [:wheat "wheat" 'wheat]
       (let [not-nil? (complement nil?)]
         (filter not-nil? [nil :wheat nil "wheat" nil 'wheat nil])))
 ;this let is a variable that can only be used in deeper scopes

  "Partial functions allow procrastination"
  (= 20 (let [multiply-by-5 (partial * 5)]
          (multiply-by-5 4)))

  "Don't forget: first things first"
  (= [:a :b :c :d]
       (let [ab-adder (partial concat [:a :b])]
         (ab-adder [:c :d])))

  "Functions can join forces as one 'composed' function"
  (= 16 (let [inc-and-square (comp square inc)]
          (inc-and-square 3)))
 ; comp takes the functions f (square) g (inc) and does f(g(x)) (aka composition)

  "Have a go on a double dec-er"
  (= 8 (let [double-dec (comp dec dec)]
          (double-dec 10)))

  "Be careful about the order in which you mix your functions"
  (= 99 (let [square-and-dec (comp dec square)]
          (square-and-dec 10))))
