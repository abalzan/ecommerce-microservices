use ecommerce;

CREATE TABLE `user` (
`ID` bigint(20) NOT NULL AUTO_INCREMENT,
`USER_NAME` varchar(100) NOT NULL,
`FIRST_NAME` varchar(100) NOT NULL,
`LAST_NAME` varchar(100) NOT NULL,
`SEX` varchar(1) NOT NULL,
`MEMBER_TYPE` varchar(100) NOT NULL,
`REGISTRATION_DATE` date NOT NULL,
PRIMARY KEY (`ID`),
UNIQUE KEY `UK_lmuw5yn9r834i8u1ry9jvjft4` (`REGISTRATION_DATE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;