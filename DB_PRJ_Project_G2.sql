drop table [admin], answer, questionResult, question, quizResult, post, lesson, mooc, purchasedCourse, [certificate], [user], country, course, lecturer, organization

-- Create the tables
CREATE TABLE [admin] (
	username VARCHAR(50) PRIMARY KEY,
	[password] VARCHAR(50)
);
GO

CREATE TABLE country (
	countryID INT PRIMARY KEY,
	name NVARCHAR(50)
);
GO

CREATE TABLE organization (
	[ID] INT IDENTITY(1,1) PRIMARY KEY, -- giá trị bắt đầu là 1, giá trị tăng thêm là 1
	name VARCHAR(50),
	logo TEXT,
	[description] NVARCHAR(50)
);
GO

CREATE TABLE [user] (
	[ID] INT IDENTITY(1,1) PRIMARY KEY,
	avatar TEXT,
	username VARCHAR(50),
	[password] VARCHAR(50),
	email VARCHAR(50),
	[firstName] NVARCHAR(50),
	[lastName] NVARCHAR(50),
	birthday DATE,
	countryID INT FOREIGN KEY REFERENCES [country],
	[status] int
);
GO

CREATE TABLE lecturer (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	userID VARCHAR(50) NOT NULL,
	organizationID VARCHAR(50) NOT NULL
);

CREATE TABLE course (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	title NVARCHAR(50) NOT NULL,
	[description] NVARCHAR(50),
	organizationID INT NOT NULL,
	lecturerID INT NOT NULL,
	price INT NOT NULL,
	FOREIGN KEY (organizationID) REFERENCES organization(ID),
	FOREIGN KEY (lecturerID) REFERENCES lecturer(ID)
);
GO

CREATE TABLE [certificate] (
	userID INT NOT NULL,
	courseID INT NOT NULL,
	certificateURL text,
	PRIMARY KEY (userID, courseID),
	FOREIGN KEY (userID) REFERENCES [user](ID),
	FOREIGN KEY (courseID) REFERENCES course(ID)
);
GO

CREATE TABLE purchasedCourse (
	userID INT NOT NULL,
	courseID INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES [user](ID),
	FOREIGN KEY (courseID) REFERENCES course(ID)
);
GO

CREATE TABLE mooc (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	courseID INT NOT NULL,
	[index] INT NOT NULL,
	title NVARCHAR(50),
	[description] NVARCHAR(50),
	FOREIGN KEY (courseID) REFERENCES course(ID)
);
GO

CREATE TABLE lesson (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	moocID INT NOT NULL,
	title NVARCHAR(50),
	type INT NOT NULL,
	FOREIGN KEY (moocID) REFERENCES mooc(ID)
);
GO

CREATE TABLE post (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	content NVARCHAR(50) NOT NULL,
	lessonID INT,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID)
);
GO

CREATE TABLE question (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	content TEXT NOT NULL,
    type INT NOT NULL,
	point INT
);
GO

CREATE TABLE answer (
	[ID] INT IDENTITY(1,1),
	content TEXT,
	correct BIT NOT NULL, --True: 1, False: 0
	questionID INT NOT NULL,
	FOREIGN KEY (questionID) REFERENCES question(ID)
);
GO

CREATE TABLE quizResult (
	ID INT NOT NULL PRIMARY KEY,
	lessonID INT,
	userID INT,
	dateSubmit DATE,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID),
	FOREIGN KEY (userID) REFERENCES [user](ID)
);
GO

CREATE TABLE questionResult (
	quizResultID INT,
	questionID INT,
	correct BIT NOT NULL
	FOREIGN KEY (quizResultID) REFERENCES quizResult(ID),
	FOREIGN KEY (questionID) REFERENCES question(ID)
);
GO

-- insert data

INSERT INTO country(countryID, [name]) VALUES
(1, 'United States'),
(2, 'Canada'),
(3, 'Mexico'),
(4, 'Germany'),
(5, 'France'),
(6, 'Italy'),
(7, 'Spain'),
(8, 'China'),
(9, 'Japan'),
(10, 'South Korea'),
(11, 'Chile'),
(12, 'India'),
(13, 'Peru'),
(14, 'South Korea'),
(15, 'Thailand'),
(16, 'Vietnam'),
(17, 'Indonesia'),
(18, 'Philippines'),
(19, 'Malaysia'),
(20, 'Singapore')
GO
INSERT INTO [admin](username, [password]) VALUES
('admin','0e7517141fb53f21ee439b355b5a1d0a'),
('quantri','0e7517141fb53f21ee439b355b5a1d0a')
GO
INSERT INTO [user](avatar, username, [password], email, [firstName], [lastName], birthday,countryID, [status]) VALUES
('https://example.com/avatar.jpg', 'user1', 'abcdef', 'user1@example.com', 'John', 'Doe', '1990-01-01', 16, 1),
('https://example.com/avatar.jpg', 'dylan12', '12345678', 'dylan@example.com', 'Dylan', 'Nguyen', '2003-10-12', 16, 1),
('https://example.com/avatar1.jpg', 'diemhuong1210', '12345678', 'dh1210@example.com', 'Huong', 'Nguyen', '2003-10-10', 16, 1)
GO

SELECT * FROM [user] LIMIT 2;
