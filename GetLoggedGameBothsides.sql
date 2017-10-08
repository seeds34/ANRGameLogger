select 
summary.gameid gameid,
summary.locationname locationname,
summary.wintype,summary.playeddate,
playerOne.playername playerOneName,
playerOne.score playerOneScore,
playerOne.winflag playerOneWinFlag,
playerOne.deckname playerOneDeckName,
playerOne.identName playerOneID,
playerOne.nrdbcode playerOnenrdbcode,
playerTwo.playername playerTwoName,
playerTwo.score playerTwoScore,
playerTwo.winflag playerTwoWinFlag,
playerTwo.deckname playerTwoDeckName,
playerTwo.identName playerTwoID,
playerTwo.nrdbcode playerTwonrdbcode 

from
(select lgs._id, lgs.gameid, lgs.playerside, d.name deckname, i.name identName, i.nrdbcode, p.playername, lgs.score, lgs.winflag from loggedgames lgs inner join players p on p._id = lgs.playerid inner join decks d on d._id = lgs.deckid inner join identities i on i._id = d.identity where lgs.playerside = 1) playerOne,

(select lgs._id, lgs.gameid, lgs.playerside, d.name deckname, i.name identName, i.nrdbcode, p.playername, lgs.score, lgs.winflag from loggedgames lgs inner join players p on p._id = lgs.playerid inner join decks d on d._id = lgs.deckid inner join identities i on i._id = d.identity where lgs.playerside = 2) playerTwo, 

(select lgs.gameid, l.locationname, lgs.wintype, lgs.playeddate from loggedgames lgs inner join locations l on l._id = lgs.locatioid group by lgs.gameid, l.locationname, lgs.wintype, lgs.playeddate) summary  

where playerOne.gameid = playerTwo.gameid and playerOne.gameid = summary.gameid;