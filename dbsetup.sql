CREATE TABLE `employee` (  
  `id` int(11) NOT NULL,
  `parentid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,  
  `designation` varchar(50) NOT NULL,  
  `mailid` varchar(50) DEFAULT NULL,
  `mobileno` varchar(15) DEFAULT NULL,  
  `experience` varchar(4) DEFAULT NULL,
  `tags` varchar(2000) DEFAULT NULL,
  `waittime` varchar(10) DEFAULT NULL,
  `value` varchar(10) DEFAULT NULL,
  `resource` varchar(1) NOT NULL
);

CREATE TABLE `resource` (  
  `id` int(11) NOT NULL,
  `islead` varchar(1) NOT NULL,
  `currentleadid` int(11) NOT NULL,
  `currentparentid` int(11) NOT NULL,
  `currentavailability` varchar(1) NOT NULL,  
  `notavailtill` varchar(15) NOT NULL,  
  `interestedbidids` varchar(100) DEFAULT NULL,
  `fixedbidid` varchar(11) DEFAULT NULL,  
  `fixedfrom` varchar(15) DEFAULT NULL,
  `fixedtill` varchar(15) DEFAULT NULL
);

CREATE TABLE `teams` (  
  `teamid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `leadid` int(11) NOT NULL,
  `parentid` int(11) NOT NULL,
  `description` varchar(2000) NOT NULL,  
  `startdate` varchar(15) NOT NULL,  
  `enddate` varchar(15) DEFAULT NULL
);

CREATE TABLE `user` (  
  `userid` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `admin` varchar(1) NOT NULL
);

CREATE TABLE `empseq` (  
  `seqno` int(11) NOT NULL
);

CREATE TABLE `teamseq` (  
  `seqno` int(11) NOT NULL
);

CREATE TABLE `tags` (  
  `tags` varchar(20000) NOT NULL
);


drop table employee;

drop table resource;

drop table seq;


insert into user(userid,username,password,admin) values(1,'admin','19223a7bbd7325516f069df18b50','Y');
insert into employee(id,parentid,name,designation,mailid,mobileno,experience,tags,waittime,value,resource) values(1,0,'admin','','','','','','','','Y');
insert into tags(tags) values('Java');
select * from user;

select * from employee;

select * from resource;

select * from tags;

SELECT userid, username, password, admin from .user where userid='2' and password='19223a7bbd7325516f069df18b50';