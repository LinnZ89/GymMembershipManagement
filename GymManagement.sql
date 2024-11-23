CREATE TABLE Admins (
    id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL
);

INSERT INTO Admins (username, password) VALUES ('linhnh089', 'Linh123.');
