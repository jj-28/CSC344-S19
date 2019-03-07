(ns main)


(defn nand [q-list]
  (cond

    (and (boolean? (second q-list)) (false? (second q-list))) true
    (and (boolean? (second q-list)) (true? (second q-list))) false
    (not(apply distinct? (rest q-list)))  (distinct q-list)

    (distinct? (remove boolean? (rest q-list) )) (distinct q-list)

    (and (boolean? (rest q-list)) (symbol? (nth q-list 3))) false


    ;Needs to be the last clause
    ;(some? (boolean?(false? (rest q-list)))) true
    (some? (true? (rest q-list ))) (filter symbol? q-list)






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


(defn simp [uneval-list]
  (apply (resolve (symbol (first uneval-list))) [uneval-list])
  )
;(cond
;  ;Falses

;  ()
;
;  :default "gimme dattttt"
;  )