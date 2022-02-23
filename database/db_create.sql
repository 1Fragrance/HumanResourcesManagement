CREATE TABLE IF NOT EXISTS `position` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    minSalary FLOAT NULL,
    maxSalary FLOAT NULL
);

CREATE TABLE IF NOT EXISTS `office` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    internalName VARCHAR(255) NOT NULL,
    streetAddress VARCHAR(255) NOT NULL,
    postalCode VARCHAR(255) NULL,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS `department` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    officeId INT NULL,

    FOREIGN KEY (officeId) REFERENCES office(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `employee` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NULL,
    phoneNumber VARCHAR(255) NULL,
    hireDate DATE NOT NULL,
    salary FLOAT NULL,
    email VARCHAR(255) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL,
    status INT NOT NULL,

    positionId INT NULL,
    departmentId INT NULL,
    FOREIGN KEY (positionId) REFERENCES `position`(id) ON DELETE SET NULL,
    FOREIGN KEY (departmentId) REFERENCES department(id) ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS `positionHistory` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    startDate DATE NOT NULL,
    endDate DATE NULL,

    employeeId INT NOT NULL,
    positionId INT NOT NULL,
    departmentId INT NOT NULL,

    FOREIGN KEY (employeeId) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (positionId) REFERENCES `position`(id) ON DELETE CASCADE,
    FOREIGN KEY (departmentId) REFERENCES department(id) ON DELETE CASCADE
);








