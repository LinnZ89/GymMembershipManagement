CREATE TABLE Members (
    memberId VARCHAR(255) NOT NULL,
    m_name VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY (memberId)
);

CREATE TABLE Trainers (
    trainerId VARCHAR(255) NOT NULL,
    t_name VARCHAR(255),
    specialization VARCHAR(255),
    experience_years INT,
    PRIMARY KEY (trainerId)
);

CREATE TABLE MembershipPlans (
    planId VARCHAR(255) NOT NULL,
    Day_Start DATE,
    Day_End DATE,
    planType VARCHAR(255),
    price DECIMAL(10, 2),
    status VARCHAR(20),
    memberId VARCHAR(255),
    FOREIGN KEY (memberId) REFERENCES Members(memberId),
    PRIMARY KEY (planId)
);

CREATE TABLE TrainingSessions (
    sessionId VARCHAR(255) NOT NULL,
    Day_Start DATE,
    Day_End DATE,
    sessionLocation VARCHAR(255),
    memberId VARCHAR(255),
    trainerId VARCHAR(255),
    FOREIGN KEY (memberId) REFERENCES Members(memberId),
    FOREIGN KEY (trainerId) REFERENCES Trainers(trainerId),
    PRIMARY KEY (sessionId)
);

CREATE TABLE MemberProgress (
    progressId VARCHAR(255) NOT NULL,
    progressDate DATE,
    muscleTrain VARCHAR(255),
    bodyweight FLOAT,
    height FLOAT,
    BMI FLOAT,
    memberId VARCHAR(255),
    FOREIGN KEY (memberId) REFERENCES Members(memberId),
    PRIMARY KEY (progressId)
);

CREATE TABLE Payments (
    paymentId VARCHAR(255) NOT NULL,
    datePay DATE,
    loan INT,
    moneyPaid INT,
    paymentStatus VARCHAR(20),
    paymentMethod VARCHAR(50),
    memberId VARCHAR(255),
    FOREIGN KEY (memberId) REFERENCES Members(memberId),
    PRIMARY KEY (paymentId)
);
