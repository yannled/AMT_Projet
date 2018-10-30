CREATE DATABASE AMTProjectDatabase;

USE AMTProjectDatabase;

CREATE TABLE tbStatus (
	statusId INT UNSIGNED PRIMARY KEY,
	status varchar(25) NOT NULL UNIQUE
);

CREATE TABLE tbPrivilege (
	privilegeId INT UNSIGNED PRIMARY KEY,
	privilege varchar(25) NOT NULL UNIQUE
);

CREATE TABLE tbUser (
	userId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	usrLastName varchar(255) NOT NULL,
    userFirstName varchar(255) NOT NULL,
    userEmail varchar(255) NOT NULL UNIQUE,
	userSel char(32) NOT NULL,
	userPassword char(32) NOT NULL,
	userAvatar blob,
	privilegeId INT UNSIGNED NOT NULL,
	statusId INT UNSIGNED NOT NULL,
	CONSTRAINT fk_tbUser_tbStatus FOREIGN KEY (statusId) REFERENCES tbStatus (statusId) ON UPDATE CASCADE,
	CONSTRAINT fk_tbUser_tbPrivilege FOREIGN KEY (privilegeId) REFERENCES tbPrivilege (privilegeId) ON UPDATE CASCADE
);


CREATE TABLE tbProject (
	projectId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	projectName varchar(255) NOT NULL,
    projectDescrition TEXT,
	projectCreationDate datetime DEFAULT CURRENT_TIMESTAMP,
	APIKey INT UNSIGNED NOT NULL UNIQUE,
	APISecret varchar(255) NOT NULL UNIQUE
);

CREATE TABLE tbUserProject (
	userId INT UNSIGNED NOT NULL,
	projectId INT UNSIGNED NOT NULL,
	CONSTRAINT pk_user_project PRIMARY KEY (userId, projectId),
	CONSTRAINT fk_tbUserProject_tbProject FOREIGN KEY (projectId) REFERENCES tbProject (projectId) ON UPDATE CASCADE,
	CONSTRAINT fk_tbUserProject_tbUser FOREIGN KEY (userId) REFERENCES tbUser (userId) ON UPDATE CASCADE
);
