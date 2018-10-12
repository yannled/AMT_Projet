USE AMTProjectDatabase;

INSERT INTO tbStatus(statusId, status)
VALUES (0, 'Inactive'),
	   (1, 'Active');
	   
INSERT INTO tbPrivilege(privilegeId, privilege)
VALUES (0, 'User'),
	   (1, 'Administrator');
	   
INSERT INTO tbUser (lastName, firstName, email, userSel, password, userAvatar, privilegeId, statusId)
VALUES ('Schär', 'Joel', 'joel.schar@heig-vd.ch', MD5(UNIX_TIMESTAMP()), MD5(CONCAT(MD5(UNIX_TIMESTAMP()),'joelS')), 1, 1),
VALUES ('Lederrey', 'Yann', 'yann.lederrey@heig-vd.ch', MD5(UNIX_TIMESTAMP()), MD5(CONCAT(MD5(UNIX_TIMESTAMP()),'yannL')), 1, 1),
VALUES ('Neto', 'Patrick', 'patrick.neto@heig-vd.ch', MD5(UNIX_TIMESTAMP()), MD5(CONCAT(MD5(UNIX_TIMESTAMP()),'patrickN')), 1, 1),
VALUES ('Henriquet', 'Steve', 'steve.henriquet@heig-vd.ch', MD5(UNIX_TIMESTAMP()), MD5(CONCAT(MD5(UNIX_TIMESTAMP()),'steveH')), 0, 1),
VALUES ('inactif', 'user', 'user.inactif@heig-vd.ch', MD5(UNIX_TIMESTAMP()), MD5(CONCAT(MD5(UNIX_TIMESTAMP()),'inactif')), 0, 0);


INSERT INTO tbProject (projectName, projectDescrition, projectCreationDate, APIKey, APISecret)
VALUES ('Lorem