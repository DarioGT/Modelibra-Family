

/*********************************************************************
 Reference
*********************************************************************/


/*********************************************************************
 Member
*********************************************************************/

CREATE TABLE Member
(
  oid  BIGINT NOT NULL,
  code  VARCHAR(16) NOT NULL,
  password  VARCHAR(16) NOT NULL,
  lastName  VARCHAR(32) NOT NULL,
  firstName  VARCHAR(32) NOT NULL,
  email  VARCHAR(80) NOT NULL,
  receiveEmail  BIT NOT NULL,
  role  VARCHAR(16) NOT NULL,
  startDate  DATE(16) NOT NULL,
  CONSTRAINT PK_Member PRIMARY KEY (oid),
  CONSTRAINT UK_Member UNIQUE (code)
);


/*********************************************************************
 Applicant
*********************************************************************/

CREATE TABLE Applicant
(
  oid  BIGINT NOT NULL,
  code  VARCHAR(16) NOT NULL,
  password  VARCHAR(16) NOT NULL,
  lastName  VARCHAR(32) NOT NULL,
  firstName  VARCHAR(32) NOT NULL,
  email  VARCHAR(80) NOT NULL,
  receiveEmail  BIT NOT NULL,
  role  VARCHAR(16) NOT NULL,
  startDate  DATE(16) NOT NULL,
  CONSTRAINT PK_Applicant PRIMARY KEY (oid),
  CONSTRAINT UK_Applicant UNIQUE (code)
);


/*********************************************************************
 CountryName
*********************************************************************/

CREATE TABLE CountryName
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  country  VARCHAR NULL,
  CONSTRAINT PK_CountryName PRIMARY KEY (oid),
  CONSTRAINT UK_CountryName UNIQUE (code)
);


/*********************************************************************
 CountryLanguage
*********************************************************************/

CREATE TABLE CountryLanguage
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  language  VARCHAR NULL,
  CONSTRAINT PK_CountryLanguage PRIMARY KEY (oid),
  CONSTRAINT UK_CountryLanguage UNIQUE (code)
);


/*********************************************************************
 SecurityRole
*********************************************************************/

CREATE TABLE SecurityRole
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  CONSTRAINT PK_SecurityRole PRIMARY KEY (oid),
  CONSTRAINT UK_SecurityRole UNIQUE (code)
);


/*********************************************************************
 FKs
*********************************************************************/


/*********************************************************************
 Member
*********************************************************************/



/*********************************************************************
 Applicant
*********************************************************************/



/*********************************************************************
 CountryName
*********************************************************************/



/*********************************************************************
 CountryLanguage
*********************************************************************/



/*********************************************************************
 SecurityRole
*********************************************************************/

 
/*********************************************************************
 OI
*********************************************************************/
 
CREATE SEQUENCE S_OI
  INCREMENT BY 1
  START WITH 1;
 
CREATE OR REPLACE PROCEDURE P_OI (newOi OUT NUMBER)
IS
BEGIN
  SELECT S_OI.NEXTVAL
  INTO newOi
  FROM DUAL;
END;
/


/*********************************************************************
----------------------------------------------------------------------
*********************************************************************/
