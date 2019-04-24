% rectangle([X,Y,Width,Height]).
inBound([P,Z], rectangle([X,Y,Width,Height]) ) :-
P > X, 
Z > Y,
P=< Width, 
Z =< Height.