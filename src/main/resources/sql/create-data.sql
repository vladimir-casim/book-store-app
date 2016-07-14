-- delete old data
delete from book;
delete from author;
delete from publisher;
delete from genre;

-- add few aouthors
insert into author values(1, 'Jobs1', 'Steve1');
insert into author values(2, 'Jobs2', 'Steve2');
insert into author values(3, 'Jobs3', 'Steve3');
insert into author values(4, 'Jobs4', 'Steve4');

-- add few publishers
insert into publisher values(1, 'Ecsmo');
insert into publisher values(2, 'OReilly');
insert into publisher values(3, 'Litres');

-- add some tasks
insert into genre values(1, 'Fiction');
insert into genre values(2, 'Horror');

-- add some books
insert into book values (1, 1, 3, 3, "title1", "description_1", null, 12, null, "2012-10-10", "English", 123, 532 );
insert into book values (2, 2, 2, 2, "title2", "description_2", null, 12, null, "2013-11-11", "Russian", 123, 532 );
insert into book values (3, 1, 4, 1, "title3", "description_3", null, 12, null, "2014-12-12", "Romanian", 123, 532 );

