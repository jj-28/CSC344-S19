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

(defn nand [q-list]
  (cond
    ; (nand false)
    ;(and (boolean? (second q-list)) (false? (second q-list))) true
    (and (= 2 (count q-list)) (false? (second q-list))) true
    (and (= 2 (count q-list)) (true? (second q-list))) false
    (and (= 2 (count q-list)) (= (first q-list) (first (second q-list)))) (second (second q-list))
    (and (= 3 (count q-list) ) (= (second q-list) (second (nth q-list 2)) ))  true


    ;(and (seq? (second q-list)) (identical? (first q-list) (first (second q-list)))) (second (second q-list))
    ; (nand true)
    ;(and (boolean? (second q-list)) (true? (second q-list))) false


    ;(not (apply distinct? (rest q-list))) (distinct q-list)
    ;(nand (nand x))


    ;


    ;(and (identical? (first q-list) (first (nth q-list 2)) )
    ;     (identical? (second q-list) (second (nth q-list 2)))) true

    ;(and (boolean? (rest q-list)) (symbol? (nth q-list 3))) false

    ;(and (> 1 (count (remove boolean? (rest q-list)))) (= 1 (count (remove ? (rest q-list)))) ) true
    ;Needs to be the last clause
    ;(some? (boolean?(false? (rest q-list)))) true (some? (true? (rest q-list))) (filter symbol? q-list)






    ;Falses
    ;only one false
    ;  (= 1 (count (filter false? (rest q-list) )))  true
    ;only one true
    ;(= 1 (count (filter true? (rest q-list) )))  false
    ;;SKIP NESTINGGGGG
    ;(identical?  (first q-list) (first ((last q-list))))
    ;;SKIP NESTINGGGG
    ;(= 2 (count (filter false? (rest q-list)))  ) (remove boolean? q-list)
    ;(> 2 (count (filter symbol? (rest q-list) ))) (remove boolean? (rest q-list))
    ;;even number of trues, no falses/symbols (x y, etc)
    ;(even? (count (remove symbol? (rest q-list)))) false
    ;;odd number of trues, no falses or symbols
    ;(odd? (count (remove symbol? (rest q-list)))) true
    ;(distinct? (remove boolean? (rest q-list) )) (distinct q-list)
    ;
    ;;(> 2 (count (filter symbol? (rest q-list)))) q-list
    ;(not (some boolean? (rest q-list)) ) (distinct (rest q-list))
    ;
    ;  ;(identical? (first q-list) (first (list (rest q-list))))  (list (last q-list))
    ;;(filter seq? (rest q-list)) COMING BACK TO THISS!!!!

    :default "gimme dattttt")
  ;(rest (first q-list) )

  )

  (defn simplify [uneval-list]
    (apply (resolve (symbol (first uneval-list))) [uneval-list])
    )
;(cond
;  ;Falses

;  ()
;
;  :default "gimme dattttt"
;  )