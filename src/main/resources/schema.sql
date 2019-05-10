CREATE TABLE Employee
(
  id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  dob     DATE        NOT NULL
);

CREATE TABLE Employment
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  employeeId  BIGINT      NOT NULL,
  dateFrom    DATE        NOT NULL,
  dateUntil   DATE        NOT NULL,
  companyName VARCHAR(50) NOT NULL,
  salary      DECIMAL     NOT NULL,
  FOREIGN KEY (employeeId) REFERENCES Employee (id) ON DELETE CASCADE
);
