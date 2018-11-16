USE AMTProjectDatabase;

INSERT INTO tbStatus(statusId, status)
VALUES (0, 'Inactive'),
	   (1, 'Active'),
	   (2, 'HasChangedPassword');
	   
INSERT INTO tbPrivilege(privilegeId, privilege)
VALUES (0, 'User'),
	   (1, 'Administrator');
	   
INSERT INTO tbUser (userLastName, userFirstName, userEmail, userPassword, userAvatar, privilegeId, statusId)
VALUES ('Schar', 'Joel', 'joel.schar@heig-vd.ch', '1000:6e159ff5f267d11c3fb1ce463a6cf455:465e34b808e7e4624f148f9bc74107161be3abca8c3d4160b3511f96eaa283feb1ee7d94650d54cb8f5d6dec37a2b09263b51b51fbba1636d2866d7dde5bcd18', "", 1, 1),
       ('Lederrey', 'Yann', 'yann.lederrey@heig-vd.ch', '1000:6e159ff5f267d11c3fb1ce463a6cf455:465e34b808e7e4624f148f9bc74107161be3abca8c3d4160b3511f96eaa283feb1ee7d94650d54cb8f5d6dec37a2b09263b51b51fbba1636d2866d7dde5bcd18', "", 1, 1),
       ('Neto', 'Patrick', 'patrick.neto@heig-vd.ch', '1000:6e159ff5f267d11c3fb1ce463a6cf455:465e34b808e7e4624f148f9bc74107161be3abca8c3d4160b3511f96eaa283feb1ee7d94650d54cb8f5d6dec37a2b09263b51b51fbba1636d2866d7dde5bcd18', "", 1, 1),
       ('Henriquet', 'Steve', 'steve.henriquet@heig-vd.ch', '1000:6e159ff5f267d11c3fb1ce463a6cf455:465e34b808e7e4624f148f9bc74107161be3abca8c3d4160b3511f96eaa283feb1ee7d94650d54cb8f5d6dec37a2b09263b51b51fbba1636d2866d7dde5bcd18',"", 0, 1),
       ('inactif', 'user', 'user.inactif@heig-vd.ch', '1000:6e159ff5f267d11c3fb1ce463a6cf455:465e34b808e7e4624f148f9bc74107161be3abca8c3d4160b3511f96eaa283feb1ee7d94650d54cb8f5d6dec37a2b09263b51b51fbba1636d2866d7dde5bcd18', "", 0, 0);
