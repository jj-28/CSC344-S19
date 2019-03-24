(ns main)


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
;change this to nand-convert, because there's only certai situations in which nand will happen

(defn simplify [q-list]
  (cond
    (and (= 2 (count q-list)) (false? (second q-list))) true
    (and (= 2 (count q-list)) (true? (second q-list))) false
    (and (seq? (second q-list)) (= (first q-list) (first (second q-list)))) (second (second q-list))
    (and (seq? (nth q-list 2)) (= (second q-list) (second (nth q-list 2)))) true
    (and (every? symbol? q-list) (not (apply distinct? (rest q-list)))) (distinct q-list)
    (every? symbol? (rest q-list)) q-list
    (and (some symbol? (rest q-list)) (some true? (rest q-list))) (remove boolean? q-list)
    (and (some symbol? (rest q-list)) (some false? (rest q-list))) true
    (and (every? boolean? (rest q-list)) (every? true? (rest q-list))) false
    :default "ya done goofed :(")

  )
(defn app [t]
  (map (fn [i]
          (conj (list  i) (symbol "nand"))
         ) t)
  )

(defn and-to-nand [q]
  (= (symbol "and") (first q))  (conj (rest q) (symbol "nand") )

  )
(defn or-to-nand [q]
  (= (symbol "or") (first q))  (cons (symbol "nand") (app (rest q)))
  )

(defn nand-convert [q]
  (cond
    ;not->nand
    (= 1 (count (rest q))) '(nand x)
    ;and->nand
    (= (symbol "and") (first q))  (conj (rest q) (symbol "nand") )
    ;or->nand
    (= (symbol "or") (first q))  (cons (symbol "nand") (app (rest q)))
    )
  :default "ya played yourself"
  )
(defn lookup [i m]
  (get m i i))

(defn nd[q]
  (map (fn [i]
         (if (seq? i)
           (nd i)
           (nand-convert i)
           ))
       q))


(defn re-up [q]
  (map (fn [i]
         (if (seq? i)
           (re-up i)
           (simplify i)
           ))
       q))

(defn bind-values [l m]
  (map (fn [i]
         (if (seq? i)
           (bind-values i m)
           (lookup i m)
           ))
       l)

  )

(defn evalexp [exp bindings]
  (simplify (re-up (nand-convert (bind-values bindings exp)))))


;(defn simplify [uneval-list]
;  (apply (resolve (symbol (first uneval-list))) [uneval-list])
;  )
