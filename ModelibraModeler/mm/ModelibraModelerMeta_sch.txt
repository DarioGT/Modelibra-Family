

/*********************************************************************
 Meta
*********************************************************************/


/*********************************************************************
 App
*********************************************************************/

CREATE TABLE App
(
  oid  BIGINT NOT NULL,
  author  VARCHAR NULL,
  prefix  VARCHAR NULL,
  oidCreated  VARCHAR NULL,
  dirNameDisplayed  VARCHAR NULL,
  blackAndWhite  VARCHAR NULL,
  CONSTRAINT PK_App PRIMARY KEY (oid)
);


/*********************************************************************
 Type
*********************************************************************/

CREATE TABLE Type
(
  oid  BIGINT NOT NULL,
  dbmsType  VARCHAR NULL,
  length  VARCHAR NULL,
  noOfDec  VARCHAR NULL,
  oidApp  BIGINT NOT NULL,
  CONSTRAINT PK_Type PRIMARY KEY (oid)
);


/*********************************************************************
 Diagram
*********************************************************************/

CREATE TABLE Diagram
(
  oid  BIGINT NOT NULL,
  author  VARCHAR NULL,
  extension  VARCHAR NULL,
  extensionName  VARCHAR NULL,
  abstractDef  VARCHAR NULL,
  persistence  VARCHAR NULL,
  oidCreated  VARCHAR NULL,
  dirNameDisplayed  VARCHAR NULL,
  size  VARCHAR NULL,
  oidApp  BIGINT NOT NULL,
  CONSTRAINT PK_Diagram PRIMARY KEY (oid)
);


/*********************************************************************
 Box
*********************************************************************/

CREATE TABLE Box
(
  oid  BIGINT NOT NULL,
  extension  VARCHAR NULL,
  extensionName  VARCHAR NULL,
  abstractDef  VARCHAR NULL,
  entry  VARCHAR NULL,
  intersection  VARCHAR NULL,
  validIntersection  VARCHAR NULL,
  manyToMany  VARCHAR NULL,
  rectangle  VARCHAR NULL,
  oidDiagram  BIGINT NOT NULL,
  CONSTRAINT PK_Box PRIMARY KEY (oid)
);


/*********************************************************************
 Line
*********************************************************************/

CREATE TABLE Line
(
  oid  BIGINT NOT NULL,
  inheritance  VARCHAR NULL,
  internal  VARCHAR NULL,
  partOfManyToMany  VARCHAR NULL,
  dir12Min  VARCHAR NULL,
  dir12Max  VARCHAR NULL,
  dir12Id  VARCHAR NULL,
  dir12Name  VARCHAR NULL,
  dir12NameVisible  VARCHAR NULL,
  dir12InsertRule  VARCHAR NULL,
  dir12DeleteRule  VARCHAR NULL,
  dir12UpdateRule  VARCHAR NULL,
  dir21Min  VARCHAR NULL,
  dir21Max  VARCHAR NULL,
  dir21Id  VARCHAR NULL,
  dir21Name  VARCHAR NULL,
  dir21NameVisible  VARCHAR NULL,
  dir21InsertRule  VARCHAR NULL,
  dir21DeleteRule  VARCHAR NULL,
  dir21UpdateRule  VARCHAR NULL,
  twin1  VARCHAR NULL,
  twin2  VARCHAR NULL,
  oidDiagram  BIGINT NOT NULL,
  oidbox1  BIGINT NOT NULL,
  oidbox2  BIGINT NOT NULL,
  CONSTRAINT PK_Line PRIMARY KEY (oid)
);


/*********************************************************************
 Item
*********************************************************************/

CREATE TABLE Item
(
  oid  BIGINT NOT NULL,
  min  VARCHAR NULL,
  max  VARCHAR NULL,
  oi  VARCHAR NULL,
  id  VARCHAR NULL,
  defaultValue  VARCHAR NULL,
  increment  VARCHAR NULL,
  oidBox  BIGINT NOT NULL,
  oidType  BIGINT NOT NULL,
  CONSTRAINT PK_Item PRIMARY KEY (oid)
);


/*********************************************************************
 FKs
*********************************************************************/


/*********************************************************************
 App
*********************************************************************/



/*********************************************************************
 Type
*********************************************************************/

ALTER TABLE Type ADD
(
  CONSTRAINT FK_TypeApp
    FOREIGN KEY (oidApp)
    REFERENCES App
);


CREATE INDEX IX_TypeApp ON Type (oidApp ASC);


/*********************************************************************
 Diagram
*********************************************************************/

ALTER TABLE Diagram ADD
(
  CONSTRAINT FK_DiagramApp
    FOREIGN KEY (oidApp)
    REFERENCES App
);


CREATE INDEX IX_DiagramApp ON Diagram (oidApp ASC);


/*********************************************************************
 Box
*********************************************************************/

ALTER TABLE Box ADD
(
  CONSTRAINT FK_BoxDiagram
    FOREIGN KEY (oidDiagram)
    REFERENCES Diagram
);


CREATE INDEX IX_BoxDiagram ON Box (oidDiagram ASC);


/*********************************************************************
 Line
*********************************************************************/

ALTER TABLE Line ADD
(
  CONSTRAINT FK_LineDiagram
    FOREIGN KEY (oidDiagram)
    REFERENCES Diagram
);

ALTER TABLE Line ADD
(
  CONSTRAINT FK_Linebox1
    FOREIGN KEY (oidbox1)
    REFERENCES Box
);

ALTER TABLE Line ADD
(
  CONSTRAINT FK_Linebox2
    FOREIGN KEY (oidbox2)
    REFERENCES Box
);


CREATE INDEX IX_LineDiagram ON Line (oidDiagram ASC);

CREATE INDEX IX_Linebox1 ON Line (oidbox1 ASC);

CREATE INDEX IX_Linebox2 ON Line (oidbox2 ASC);


/*********************************************************************
 Item
*********************************************************************/

ALTER TABLE Item ADD
(
  CONSTRAINT FK_ItemBox
    FOREIGN KEY (oidBox)
    REFERENCES Box
);

ALTER TABLE Item ADD
(
  CONSTRAINT FK_ItemType
    FOREIGN KEY (oidType)
    REFERENCES Type
);


CREATE INDEX IX_ItemBox ON Item (oidBox ASC);

CREATE INDEX IX_ItemType ON Item (oidType ASC);
 
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
