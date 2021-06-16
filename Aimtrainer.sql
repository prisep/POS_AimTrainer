DROP TABLE user CASCADE CONSTRAINTS;
CREATE TABLE user(
 username VARCHAR(30),
 password VARCHAR(30),
 highscore INTEGER, 
 lastOnline DATE
);