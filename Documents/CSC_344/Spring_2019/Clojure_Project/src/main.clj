(ns main)
;remove trues with

;(nand false) =: true checkkk
;(nand true) =: false checkkk
;(nand (nand x)) =: x checkkk
;(nand x (nand x)) =: true
;(nand x x) =: (nand x) checkkk
;(nand x y) =: (nand x y) checkk
;(nand x true) =: (nand x) checkkk
;(nand x false) =: true
;(nand true true) =: false
;(nand x y true) =: (nand x y)
;(nand x true true) =: (nand x)
;(nand true true true) =: false)
;(nand x y false) =: true
;(nand x y z) =: (nand x y z) checkkk
;
;earlier cases
;(defn simplify [q-list]
;  (cond
;    (some false? (remove symbol? q-list) ) true
;    ;(and (some symbol? (rest q-list)) (some true? (rest q-list))) (remove boolean? q-list)
;    ;(and (every? symbol? q-list) (not (apply distinct? (rest q-list)))) (distinct q-list)
;    (and (every? boolean? (rest q-list)) (every? true? (rest q-list))) false
;    (and (seq? (second q-list)) (= (first q-list) (first (second q-list)))) (second (second q-list))
;    (and (seq? (nth q-list 2 "nothing found")) (= (second q-list) (second (nth q-list 2 "nothing found")))) true
;    :default q-list
;    )
;  )
(defn d-check [l]
  (cond
    ;(some false? (remove symbol? l)) true
    (and (some symbol? (rest l)) (some true? (rest l))) (remove boolean? l)
    (and (every? symbol? l) (not (apply distinct? (rest l)))) (distinct l)
    :default l
    )
  )
(defn simplify [q-list]
  (let [f-list (distinct (remove true? q-list))] ;(d-check q-list)]
    (cond
      (some false? (remove symbol? f-list)) true
      (and (some symbol? (rest f-list)) (some true? (rest f-list))) (remove true? f-list)
      ;(and (every? symbol? f-list) (not (apply distinct? (rest f-list)))) (distinct                                                                             f-list)
      (and (every? boolean? (rest f-list)) (every? true? (rest f-list))) false
      ;(and (seq? (second f-list)) (= (first f-list) (first (second f-list)))  (not (seq? (nth f-list 2 "nein")))) (second (second f-list))
      ;(and (seq? (second f-list)) (= (first f-list) (first (second f-list))) (= 2 (count f-list)) ) (second (second f-list))
      (and (= 2 (count f-list)) (seq? (second f-list))  (= 2 (count (second f-list))))  (second (second f-list))
      (and (seq? (nth f-list 2 "nothing found")) (= (second f-list) (second (nth f-list 2 "nothing found")))) true
      :default f-list
      )
    )
  )
(defn app [t]
  (map (fn [i]
         (conj (list i) (symbol "nand"))
         ) t)
  )
(defn nand-convert [q]
  (cond
    ;not->nand
    (= 1 (count (rest q))) (conj (rest q) (symbol "nand"))

    (= (symbol "and") (first q)) (conj (list (cons (symbol "nand") (rest q))) (symbol "nand"))
    ;or->nand

    (= (symbol "or") (first q)) (cons (symbol "nand") (app (rest q)))
    :default q)

  )
(defn lookup [i m]
  (get m i i))

(defn nested [l]
  (println l "->" (simplify (map (fn [i]
                                   (if (seq? i)
                                     (nested i)
                                     i
                                     ))
                                 l)) )
  (simplify (map (fn [i]
                   (if (seq? i)
                     (nested i)
                     i
                     ))
                 l))
  )
(defn com [l]
  (nand-convert (map (fn [i]
                       (if (seq? i)
                         (com i)
                         i
                         ))
                     l))

  )
(defn bind-values [l m]
  (map (fn [i]
         (if (seq? i)
           (bind-values i m)
           (lookup i m)
           ))
       l)

  )

;(defn evalexp [exp bindings]
;  (simplify (nand-convert (bind-values bindings exp))))


(defn evalexp [exp bindings]
  (nested (com (bind-values exp bindings)))
  )

(def p1 '(and x (or x (and y (not z)))))
(def p2 '(and (and z false) (or x true false)))
(def p3 '(or true a))
