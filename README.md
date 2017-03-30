<h1><center>JDBC</center></h1>

1. Create simple database with tables:

*  <p> Users (id, name, surname, birthdate)
*  <p> Friendships (userid1, userid2, timestamp)
*  <p> Posts(id, userId, text, timestamp)
*  <p> Likes (postid, userid, timestamp)
  
2. Fill tables with data which are make sense: 
*  <p> > 1 000 users
*  <p> > 70 000 friendships
*  <p> > 300 000 likes

3. <p> Program should print out all names (only distinct) of users who has more when 100 friends and 100 likes in March 2017. 
   <p> All actions (table creation, insert data and reading) should be implemented with JDBC.