

/*********************************************************************
 Meta
*********************************************************************/


/*********************************************************************
 DomainModel
*********************************************************************/

CREATE TABLE DomainModel
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  abstraction  VARCHAR NULL,
  extension  VARCHAR NULL,
  extensionDomain  VARCHAR NULL,
  extensionDomainType  VARCHAR NULL,
  extensionModel  VARCHAR NULL,
  author  VARCHAR NULL,
  packageCode  VARCHAR NULL,
  persistent  VARCHAR NULL,
  persistenceType  VARCHAR NULL,
  persistenceRelativePath  VARCHAR NULL,
  persistenceConfig  VARCHAR NULL,
  defaultLoadSave  VARCHAR NULL,
  datePattern  VARCHAR NULL,
  session  VARCHAR NULL,
  oidDomain  BIGINT NOT NULL,
  CONSTRAINT PK_DomainModel PRIMARY KEY (oid),
  CONSTRAINT UK_DomainModel UNIQUE (oidDomain, code)
);


/*********************************************************************
 Concept
*********************************************************************/

CREATE TABLE Concept
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  abstraction  VARCHAR NULL,
  extension  VARCHAR NULL,
  extensionDomain  VARCHAR NULL,
  extensionDomainType  VARCHAR NULL,
  extensionModel  VARCHAR NULL,
  extensionConcept  VARCHAR NULL,
  extensionWithNeighbors  VARCHAR NULL,
  entitiesCode  VARCHAR NULL,
  packageCode  VARCHAR NULL,
  min  VARCHAR NULL,
  max  VARCHAR NULL,
  entry  VARCHAR NULL,
  fileName  VARCHAR NULL,
  index  VARCHAR NULL,
  display  VARCHAR NULL,
  displayType  VARCHAR NULL,
  add  VARCHAR NULL,
  remove  VARCHAR NULL,
  update  VARCHAR NULL,
  oidDomainModel  BIGINT NOT NULL,
  CONSTRAINT PK_Concept PRIMARY KEY (oid),
  CONSTRAINT UK_Concept UNIQUE (oidDomainModel, code)
);


/*********************************************************************
 Property
*********************************************************************/

CREATE TABLE Property
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  extension  VARCHAR NULL,
  extensiopnProperty  VARCHAR NULL,
  propertyClass  VARCHAR NULL,
  derived  VARCHAR NULL,
  validateType  VARCHAR NULL,
  validationType  VARCHAR NULL,
  maxLength  VARCHAR NULL,
  required  VARCHAR NULL,
  sensitive  VARCHAR NULL,
  defaultValue  VARCHAR NULL,
  autoIncrement  VARCHAR NULL,
  unique  VARCHAR NULL,
  index  VARCHAR NULL,
  reference  VARCHAR NULL,
  referenceNeighbor  VARCHAR NULL,
  display  VARCHAR NULL,
  update  VARCHAR NULL,
  displayLength  VARCHAR NULL,
  essential  VARCHAR NULL,
  scramble  VARCHAR NULL,
  whitespaceAllowed  VARCHAR NULL,
  oidConcept  BIGINT NOT NULL,
  CONSTRAINT PK_Property PRIMARY KEY (oid),
  CONSTRAINT UK_Property UNIQUE (oidConcept, code)
);


/*********************************************************************
 Neighbor
*********************************************************************/

CREATE TABLE Neighbor
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  extension  VARCHAR NULL,
  extensionNeighbor  VARCHAR NULL,
  destinationConcept  VARCHAR NULL,
  inverseNeighbor  VARCHAR NULL,
  internal  VARCHAR NULL,
  partOfManyToMany  VARCHAR NULL,
  type  VARCHAR NULL,
  min  VARCHAR NULL,
  max  VARCHAR NULL,
  unique  VARCHAR NULL,
  index  VARCHAR NULL,
  addRule  VARCHAR NULL,
  removeRule  VARCHAR NULL,
  updateRule  VARCHAR NULL,
  display  VARCHAR NULL,
  update  VARCHAR NULL,
  absorb  VARCHAR NULL,
  oidConcept  BIGINT NOT NULL,
  CONSTRAINT PK_Neighbor PRIMARY KEY (oid),
  CONSTRAINT UK_Neighbor UNIQUE (oidConcept, code)
);


/*********************************************************************
 Domain
*********************************************************************/

CREATE TABLE Domain
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  type  VARCHAR NOT NULL,
  packageCode  VARCHAR NULL,
  abstraction  VARCHAR NULL,
  defaultConstruct  VARCHAR NULL,
  packagePrefix  VARCHAR NULL,
  referenceModel  VARCHAR NULL,
  i18n  VARCHAR NULL,
  signin  VARCHAR NULL,
  signinConcept  VARCHAR NULL,
  shortTextDefaultLength  VARCHAR NULL,
  pageBlockDefaultSize  VARCHAR NULL,
  validateForm  VARCHAR NULL,
  confirmRemove  VARCHAR NULL,
  CONSTRAINT PK_Domain PRIMARY KEY (oid),
  CONSTRAINT UK_Domain UNIQUE (code, type)
);


/*********************************************************************
 FKs
*********************************************************************/


/*********************************************************************
 DomainModel
*********************************************************************/

ALTER TABLE DomainModel ADD
(
  CONSTRAINT FK_DomainModelDomain
    FOREIGN KEY (oidDomain)
    REFERENCES Domain
);


CREATE INDEX IX_DomainModelDomain ON DomainModel (oidDomain ASC);


/*********************************************************************
 Concept
*********************************************************************/

ALTER TABLE Concept ADD
(
  CONSTRAINT FK_ConceptDomainModel
    FOREIGN KEY (oidDomainModel)
    REFERENCES DomainModel
);


CREATE INDEX IX_ConceptDomainModel ON Concept (oidDomainModel ASC);


/*********************************************************************
 Property
*********************************************************************/

ALTER TABLE Property ADD
(
  CONSTRAINT FK_PropertyConcept
    FOREIGN KEY (oidConcept)
    REFERENCES Concept
);


CREATE INDEX IX_PropertyConcept ON Property (oidConcept ASC);


/*********************************************************************
 Neighbor
*********************************************************************/

ALTER TABLE Neighbor ADD
(
  CONSTRAINT FK_NeighborConcept
    FOREIGN KEY (oidConcept)
    REFERENCES Concept
);


CREATE INDEX IX_NeighborConcept ON Neighbor (oidConcept ASC);


/*********************************************************************
 Domain
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
