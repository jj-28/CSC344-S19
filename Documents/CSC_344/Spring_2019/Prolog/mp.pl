rectangle([X,Y,Width,Height]).
% inBound([P,Z]).
% inBound([X,Y]) :- P > 0, Z > 0, P =< Width, Z =< Height.
inBound([P,Z], rectangle([X,Y,Width,Height]) ) :-
P > 0, 
Z > 0,
P=< Width, 
Z =< Height.