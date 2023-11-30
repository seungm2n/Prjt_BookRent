-- bookrent.tb_book definition
CREATE TABLE `tb_book` (
    `bookid` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    `author` varchar(255) DEFAULT NULL,
    `regDate` date DEFAULT NULL,
    `state` bit(1) NOT NULL,
    `title` varchar(255) DEFAULT NULL
);

-- bookrent.tb_member definition

CREATE TABLE `tb_member` (
    `userid` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL
);

-- bookrent.tb_rent definition

CREATE TABLE `tb_rent` (
    `rentid` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `rentDate` date DEFAULT NULL,
    `state` bit(1) NOT NULL,
    `article_bookid` int(11) DEFAULT NULL,
    `userid` int(11) DEFAULT NULL,
    CONSTRAINT FOREIGN KEY (`article_bookid`) REFERENCES `tb_book` (`bookid`),
    CONSTRAINT FOREIGN KEY (`userid`) REFERENCES `tb_member` (`userid`)
);