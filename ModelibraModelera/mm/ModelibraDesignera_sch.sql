

/*********************************************************************
 Designer
*********************************************************************/


/*********************************************************************
 MetaDomain
*********************************************************************/

CREATE TABLE MetaDomain
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  description  VARCHAR NULL,
  CONSTRAINT PK_MetaDomain PRIMARY KEY (oid),
  CONSTRAINT UK_MetaDomain UNIQUE (code)
);


/*********************************************************************
 MetaModel
*********************************************************************/

CREATE TABLE MetaModel
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  author  VARCHAR NULL,
  description  VARCHAR NULL,
  oidMetaDomain  BIGINT NOT NULL,
  CONSTRAINT PK_MetaModel PRIMARY KEY (oid),
  CONSTRAINT UK_MetaModel UNIQUE (oidMetaDomain, code)
);


/*********************************************************************
 MetaConcept
*********************************************************************/

CREATE TABLE MetaConcept
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  entry  BIT NOT NULL,
  oidMetaModel  BIGINT NOT NULL,
  CONSTRAINT PK_MetaConcept PRIMARY KEY (oid),
  CONSTRAINT UK_MetaConcept UNIQUE (oidMetaModel, code)
);


/*********************************************************************
 MetaConceptGraphic
*********************************************************************/

CREATE TABLE MetaConceptGraphic
(
  oid  BIGINT NOT NULL,
  x  INTEGER NOT NULL,
  y  INTEGER NOT NULL,
  width  INTEGER NOT NULL,
  height  INTEGER NOT NULL,
  oidMetaConcept  BIGINT NOT NULL,
  CONSTRAINT PK_MetaConceptGraphic PRIMARY KEY (oid)
);


/*********************************************************************
 MetaProperty
*********************************************************************/

CREATE TABLE MetaProperty
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  id  BIT NOT NULL,
  increment  BIT NOT NULL,
  value  BIT NOT NULL,
  init  VARCHAR NULL,
  oidMetaConcept  BIGINT NOT NULL,
  oidMetaType  BIGINT NULL,
  CONSTRAINT PK_MetaProperty PRIMARY KEY (oid),
  CONSTRAINT UK_MetaProperty UNIQUE (oidMetaConcept, code)
);


/*********************************************************************
 MetaType
*********************************************************************/

CREATE TABLE MetaType
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  base  VARCHAR NOT NULL,
  length  INTEGER NULL,
  CONSTRAINT PK_MetaType PRIMARY KEY (oid),
  CONSTRAINT UK_MetaType UNIQUE (code)
);


/*********************************************************************
 MetaNeighbor
*********************************************************************/

CREATE TABLE MetaNeighbor
(
  oid  BIGINT NOT NULL,
  code  VARCHAR NOT NULL,
  id  BIT NOT NULL,
  min  INTEGER NOT NULL,
  max  VARCHAR NOT NULL,
  internal  BIT NOT NULL,
  oidmetaSourceConcept  BIGINT NOT NULL,
  oidmetaDestinationConcept  BIGINT NOT NULL,
  CONSTRAINT PK_MetaNeighbor PRIMARY KEY (oid),
  CONSTRAINT UK_MetaNeighbor UNIQUE (oidmetaSourceConcept, oidmetaDestinationConcept, code)
);


/*********************************************************************
 FKs
*********************************************************************/


/*********************************************************************
 MetaDomain
*********************************************************************/



/*********************************************************************
 MetaModel
*********************************************************************/

ALTER TABLE MetaModel ADD
(
  CONSTRAINT FK_MetaModelMetaDomain
    FOREIGN KEY (oidMetaDomain)
    REFERENCES MetaDomain
);


CREATE INDEX IX_MetaModelMetaDomain ON MetaModel (oidMetaDomain ASC);


/*********************************************************************
 MetaConcept
*********************************************************************/

ALTER TABLE MetaConcept ADD
(
  CONSTRAINT FK_MetaConceptMetaModel
    FOREIGN KEY (oidMetaModel)
    REFERENCES MetaModel
);


CREATE INDEX IX_MetaConceptMetaModel ON MetaConcept (oidMetaModel ASC);


/*********************************************************************
 MetaConceptGraphic
*********************************************************************/

ALTER TABLE MetaConceptGraphic ADD
(
  CONSTRAINT FK_MetaConceptGraphicMetaCon1
    FOREIGN KEY (oidMetaConcept)
    REFERENCES MetaConcept
);


CREATE INDEX IX_MetaConceptGraphicMetaCon1 ON MetaConceptGraphic (oidMetaConcept ASC);


/*********************************************************************
 MetaProperty
*********************************************************************/

ALTER TABLE MetaProperty ADD
(
  CONSTRAINT FK_MetaPropertyMetaConcept
    FOREIGN KEY (oidMetaConcept)
    REFERENCES MetaConcept
);

ALTER TABLE MetaProperty ADD
(
  CONSTRAINT FK_MetaPropertyMetaType
    FOREIGN KEY (oidMetaType)
    REFERENCES MetaType
);


CREATE INDEX IX_MetaPropertyMetaConcept ON MetaProperty (oidMetaConcept ASC);

CREATE INDEX IX_MetaPropertyMetaType ON MetaProperty (oidMetaType ASC);


/*********************************************************************
 MetaType
*********************************************************************/



/*********************************************************************
 MetaNeighbor
*********************************************************************/

ALTER TABLE MetaNeighbor ADD
(
  CONSTRAINT FK_MetaNeighbormetaSourceCon1
    FOREIGN KEY (oidmetaSourceConcept)
    REFERENCES MetaConcept
);

ALTER TABLE MetaNeighbor ADD
(
  CONSTRAINT FK_MetaNeighbormetaDestinati2
    FOREIGN KEY (oidmetaDestinationConcept)
    REFERENCES MetaConcept
);


CREATE INDEX IX_MetaNeighbormetaSourceCon1 ON MetaNeighbor (oidmetaSourceConcept ASC);

CREATE INDEX IX_MetaNeighbormetaDestinati2 ON MetaNeighbor (oidmetaDestinationConcept ASC);
 
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
