CREATE TABLE SalesOffice
(
    number   BIGINT NOT NULL PRIMARY KEY IDENTITY,
    location NVARCHAR(50),
    managerId BIGINT UNIQUE,
);

CREATE TABLE Employee
(
     id BIGINT NOT NULL PRIMARY KEY IDENTITY,
     firstName NVARCHAR(15),
     lastName NVARCHAR(15),
     username VARCHAR(15) UNIQUE NOT NULL,
     password VARCHAR(64) NOT NULL,
     officeNumber BIGINT REFERENCES SalesOffice(number) ON DELETE SET NULL,
);

ALTER TABLE SalesOffice ADD CONSTRAINT fkManagerId FOREIGN KEY (managerId) REFERENCES Employee(id)  ON DELETE SET NULL;

CREATE TABLE [Owner]
(
    id BIGINT NOT NULL PRIMARY KEY IDENTITY,
    firstName NVARCHAR(15),
    lastName NVARCHAR(15),
);
CREATE TABLE [Property]
(
    id BIGINT NOT NULL PRIMARY KEY IDENTITY,
    city NVARCHAR(15),
    state NVARCHAR(15),
    address NVARCHAR(30),
    postCode NVARCHAR(10),
    officeNumber BIGINT NOT NULL REFERENCES SalesOffice(number) ON DELETE CASCADE,

);

CREATE TABLE PropertyOwner
(
    propertyId BIGINT NOT NULL REFERENCES Property(id) ON DELETE CASCADE,
    ownerId BIGINT NOT NULL REFERENCES Owner(id) ON DELETE CASCADE,
    [percent] DECIMAL(3,2),
    CHECK ([percent] <= 1),
);