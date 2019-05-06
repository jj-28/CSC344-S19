% place_mirrors(, [[2,2,3],[9,2,4]], X).

% first_emitter([0, FE_Y]) :- FE_Y > 10, FE_Y > 0 .
% last_emitter([0, LE_Y]) :- LE_Y > 10, LE_Y > 0 .

move([X,Y], [X1, Y] ) :- X1 is X+1.

move([X,Y], [X, Y1] ) :- Y1 is Y+1.

move([X,Y], [X1, Y] ) :- X1 is X-1.