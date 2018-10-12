USE AMTProjectDatabase;

-- Procedure to check user

DELIMITER $$
CREATE PROCEDURE `whoIs`(`login` varchar(255), `password` char(32)) NOT DETERMINISTIC
READS SQL DATA
MAIN: BEGIN
    SELECT userId AS id, userEmail AS email FROM tbUser
    WHERE userEmail = login and userPassword = hashage(userSel, password);
END MAIN;$$
DELIMITER ;

-- Function hashage 

DELIMITER $$
CREATE FUNCTION `hashage`(`sel` char(32), `mot` char(32)) RETURNS char(100) CHARSET utf8 NOT DETERMINISTIC
READS SQL DATA
MAIN: BEGIN
    RETURN MD5(CONCAT(sel, mot));
END MAIN;$$
DELIMITER ;