%% Travel E -> W

travel(e,w).
%% Travel W -> E

travel(w,e).

%% Possible moves.

%% first list represents the current banks that the members are on (West or East).
%%The thing in the middle is whats being transported. Farmer is automatically going with that animal. The double X's indicate that they're going in the same direction. Travel from X to Y
move([X,X,Goat,Cabbage],wolf,[Y,Y,Goat,Cabbage]) :- travel(X,Y).
move([X,Wolf,X,Cabbage],goat,[Y,Wolf,Y,Cabbage]) :- travel(X,Y).
move([X,Wolf,Goat,X],cabbage,[Y,Wolf,Goat,Y]) :- travel(X,Y).
move([X,Wolf,Goat,Cabbage],nothing,[Y,Wolf,Goat,Cabbage]) :- travel(X,Y).

%% Safe conditions.

safe([X,_,X,_]). % Goat is on the same bank as farmer.
safe([X,X,_,X]). % Wolf and cabbage are on the same bank as farmer

solve([e,e,e,e],[]).
solve(State,[FirstMove|OtherMoves]) :- move(State, FirstMove, NextState), safe(NextState), solve(NextState, OtherMoves).

% ?- length(X,7), solve([w,w,w,w],X).

% X = [goat, nothing, wolf, goat, cabbage, nothing, goat] ;

% X = [goat, nothing, wolf, goat, cabbage, nothing, goat] ;

% X = [goat, nothing, cabbage, goat, wolf, nothing, goat] ;

% X = [goat, nothing, cabbage, goat, wolf, nothing, goat] ;

% false.


% ?- length(X,7), setof(t,solve([w,w,w,w],X),_).

% X = [goat, nothing, cabbage, goat, wolf, nothing, goat] ;

% X = [goat, nothing, wolf, goat, cabbage, nothing, goat].
