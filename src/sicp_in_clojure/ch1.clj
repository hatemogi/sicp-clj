(ns sicp-in-clojure.ch1)

;; 1.1.1  Expressions

486

(+ 137 349)
(- 1000 334)

(+ 2.7 10)

(+ 21 35 12 7)

(* 25 4 12)

(+ (* 3 5) (- 10 6))

(+ (* 3 (+ (* 2 4) (+ 3 5))) (+ (- 10 7) 6))

(+ (* 3
      (+ (* 2 4)
         (+ 3 5)))
   (+ (- 10 7)
      6))

;; paredit
;; parinfer

;; 1.1.2  Naming and the Environment

(def size 2)
size

(* 5 size)

(def pi 3.14159)
(def radius 10)
(* pi (* radius radius))
(def 원주 (* 2 pi radius))

;; 1.1.3  Evaluating Combinations

(* (+ 2 (* 4 6))
   (+ 3 5 7))

;; 1.1.4  Compound Procedures

(defn square [x]
  (* x x))

(square 21)

(square (+ 2 5))

(square (square 3))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(sum-of-squares 3 4)

(defn f [a]
  (sum-of-squares (+ a 1) (* a 2)))

(f 5)

;; 1.1.5  The Substitution Model for Procedure Application

;; 1.1.6  Conditional Expressions and Predicates

(defn abs [x]
  (cond (> x 0) x
        (= x 0) 0
        (< x 0) (- x)))

(defn abs' [x]
  (cond (< x 0) (- x)
        :else x))

(defn abs'' [x]
  (if (< x 0)
    (- x)
    x))

; (and (> x 5) (< x 10))

(defn >=' [x y]
  (or (> x y) (= x y)))

(defn >='' [x y]
  (not (< x y)))

(def a 3)
(def b (+ a 1))
(cond (= a 4) 6
      (= b 4) (+ 6 7 a)
      :else 25)

(* (cond (> a b) a
         (< a b) b
         :else -1)
   (+ a 1))

;; 1.1.7  Example: Square Roots by Newton's Method

(defn average [x y]
  (/ (+ x y) 2))

(defn good-enough? [guess x]
  (< (Math/abs (- (square guess) x))
     0.001))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (recur (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9)

;; 1.1.8  Procedures as Black-Box Abstractions

(defn sqrt [x]
  (let [good-enough? (fn [guess]
                       (< (Math/abs (- (square guess) x)) 0.001))
        improve      (fn [guess]
                       (average guess (/ x guess)))
        sqrt-iter    (fn [guess]
                       (if (good-enough? guess)
                         guess
                         (recur (improve guess))))]
    (sqrt-iter 1.0)))

(sqrt 2)

;; 1.2.1  Linear Recursion and Iteration

(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))

;; linear recursive process

(factorial 6)

(defn fact-iter [product counter max-count]
  (if (> counter max-count)
    product
    (fact-iter (* counter product)
               (+ counter 1)
               max-count)))

(defn factorial [n]
  (fact-iter 1 1 n))

;; linear iterative process

(factorial 6)

;; 1.2.2  Tree Recursion

(defn fib [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else   (+ (fib (- n 1))
               (fib (- n 2)))))


(defn fib-iter [a b count]
  (if (= count 0)
    b
    (recur (+ a b) a (- count 1))))

(defn fib [n]
  (fib-iter 1 0 n))

(fib 5)
