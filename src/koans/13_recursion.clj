(ns koans.13-recursion
  (:require [koan-engine.core :refer :all]))

(defn is-even? [n]
  (if (= n 0)
    true
    (not (is-even? (dec n)))))

(defn is-even-bigint? [n]
  (loop [n   n
         acc true]
    (if (= n 0)
      acc
      (recur (dec n) (not acc)))))

;this destructuring is for setting the default values

(defn recursive-reverse [coll]
  (defn rever2 [in out]
  (if (empty? in)
    out
    (conj
     (rever2 (rest in) out)
     (first in))))
  (rever2 coll [])
  )

(defn factorial-rec [n]
  (if (= n 1)
    1
    (* n (factorial-rec (- n 1)))
  ))
;(- n 1) can be expressed as (dec n) - more idiomatic

; does not pass the factorial 100003N
(defn factorial-bigint [n]
  (if (= n 1)
    (bigint 1)
    (* (bigint n) (factorial-bigint (bigint (- n 1))))
  ))


(defn factorial [n]
  (loop [n n
         acc 1]
    (if (= n 1)
      ; remember: this value will be returned at the end of the loop
      acc
      (recur (dec n) (* n acc)))))

; below solution copied from http://www.clojurescreencasts.com/koans-walkthrough/13.html
(defn factorial-copied [n]
  (loop [n n
         res 1]
    (if (= 0 n)
      res
      (recur (dec n) (* n res)))))

(meditations
  "Recursion ends with a base case"
  (= true (is-even? 0))

  "And starts by moving toward that base case"
  (= false (is-even? 1))

  "Having too many stack frames requires explicit tail calls with recur"
  (= false (is-even-bigint? 100003N))

  "Reversing directions is easy when you have not gone far"
  (= '(1) (recursive-reverse [1]))

  "Yet it becomes more difficult the more steps you take"
  (= '(5 4 3 2 1) (recursive-reverse [1 2 3 4 5]))

  "Simple things may appear simple."
  (= 1 (factorial 1))

  "They may require other simple steps."
  (= 2 (factorial 2))

  "Sometimes a slightly bigger step is necessary"
  (= 6 (factorial 3))

  "And eventually you must think harder"
  (= 24 (factorial 4))

  "You can even deal with very large numbers"
  (< 1000000000000000000000000N (factorial 1000N))

  "But what happens when the machine limits you?"
  (< 1000000000000000000000000N (factorial 100003N)))
