drop table [admin], questionResult, answer, question, quizResult, post, lessonCompleted, lesson, mooc, orderCourse, purchasedCourse, [certificate], course, lecturer, [user], country, organization

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
	ID INT IDENTITY(1,1) PRIMARY KEY, -- giá trị bắt đầu là 1, giá trị tăng thêm là 1
	[name] VARCHAR(50),
	logo TEXT,
	[description] NVARCHAR(50)
);
GO

CREATE TABLE [user] (
	ID INT IDENTITY(1,1) PRIMARY KEY,
	avatar TEXT,
	username VARCHAR(50),
	[password] VARCHAR(50),
	email VARCHAR(50),
	[firstName] NVARCHAR(50),
	[lastName] NVARCHAR(50),
	[role] INT,
	birthday DATE,
	countryID INT FOREIGN KEY REFERENCES [country],
	[status] int
);
GO

CREATE TABLE lecturer (
	userID INT NOT NULL PRIMARY KEY,
	organizationID INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES [user](ID),
	FOREIGN KEY (organizationID) REFERENCES organization(ID)
);
GO

CREATE TABLE course (
	ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	title NVARCHAR(50) NOT NULL,
	[image] TEXT,
	[description] NVARCHAR(50),
	organizationID INT NOT NULL,
	lecturerID INT NOT NULL,
	unSalePrice DECIMAL(10, 2) NOT NULL,
	price DECIMAL(10, 2) NOT NULL,
	rate DECIMAL(2, 1) NOT NULL,
	FOREIGN KEY (organizationID) REFERENCES organization(ID),
	FOREIGN KEY (lecturerID) REFERENCES [user](ID)
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
	FOREIGN KEY (courseID) REFERENCES course(ID),
	UNIQUE (userID, courseID)
);
GO

CREATE TABLE orderCourse (
	userID INT NOT NULL,
	courseID INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES [user](ID),
	FOREIGN KEY (courseID) REFERENCES course(ID),
	UNIQUE (userID, courseID)
);
GO

CREATE TABLE mooc (
	ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	courseID INT NOT NULL,
	[index] INT NOT NULL,
	title NVARCHAR(50),
	[description] NVARCHAR(50),
	FOREIGN KEY (courseID) REFERENCES course(ID)
);
GO

CREATE TABLE lesson (
	ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	moocID INT NOT NULL,
	title NVARCHAR(50),
	[index] INT NOT NULL,
	[type] INT NOT NULL,
	[time] INT NOT NULL,
	FOREIGN KEY (moocID) REFERENCES mooc(ID)
);
GO

CREATE TABLE lessonCompleted (
	lessonID INT NOT NULL,
	userID INT NOT NULL,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID),
	FOREIGN KEY (userID) REFERENCES [user](ID),
	UNIQUE (lessonID, userID)
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
	lessonID INT NOT NULL,
	[index] INT NOT NULL,
	content TEXT NOT NULL,
    [type] INT NOT NULL,
	point INT,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID)
);
GO

CREATE TABLE answer (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	questionID INT NOT NULL,
	content TEXT,
	correct BIT NOT NULL, --True: 1, False: 0
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
	selectedAnswer INT,
	FOREIGN KEY (quizResultID) REFERENCES quizResult(ID),
	FOREIGN KEY (questionID) REFERENCES question(ID),
	FOREIGN KEY (selectedAnswer) REFERENCES answer(ID)
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
INSERT INTO [user](avatar, username, [password], email, firstName, lastName, [role], birthday,countryID, [status]) VALUES
('a.jpg', 'ttnhan', '0cc175b9c0f1b6a831c399e269772661', 'user1@example.com', 'Nhan', 'Tran Thanh', 0, '1990-01-01', 16, 1),
('a.jpg', 'dylan12', '12345678', 'dylan@example.com', 'Huong', 'Nguyen', 0, '2003-10-12', 16, 1),
('a.jpg', 'diemhuong1210', '12345678', 'dh1210@example.com', 'Duong', 'Thanh', 1, '2003-10-10', 16, 1)
GO
INSERT INTO organization([name], logo, [description]) VALUES
('FPT University', 'FPT.png', N'Trường đại học top 1 Việt Nam');
GO
INSERT INTO lecturer(userID, organizationID) VALUES
(1, 1)
GO
INSERT INTO course(title,[image],[description],organizationID,lecturerID,unSalePrice,price,rate) VALUES
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 200, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 500, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 600, 4.7),
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 200, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 500, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 600, 4.7),
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 200, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 500, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 600, 4.7),
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 200, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 500, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 600, 4.7)
GO
INSERT INTO orderCourse(userID, courseID) VALUES
(1, 2), (1, 3)
GO
INSERT INTO purchasedCourse(userID, courseID) VALUES
(1, 1)
GO
INSERT INTO mooc(courseID, [index], title, [description]) VALUES
(1, 1, N'Part 1: Hiragana 。ひらがな', ''),
(1, 2, N'Part 2: Katakana 。dgがな', '')
GO
INSERT INTO lesson(moocID, title, [index], [type], [time]) VALUES
(1, 'Nihongo', 1, 0, 1)
GO
INSERT INTO post(content, lessonID) VALUES
('baclieu.mp4', 1)
GO
INSERT INTO lesson(moocID, title, [index], [type], [time]) VALUES
(1, 'Nihongo', 2, 2, 5),
(2, 'Nihongo2', 2, 2, 5)
GO
INSERT INTO question(lessonID, [index], content, [type], point) VALUES
(2, 1, 'a.png', 0, 1),
(2, 2, 'i.png', 0, 1),
(2, 3, 'u.png', 0, 1),
(2, 4, 'e.png', 0, 1)
GO
INSERT INTO answer(questionID, content, correct) VALUES
(1, 'a', 1),
(1, 'u', 0),
(1, 'e', 0),
(1, 'i', 0),
(2, 'a', 0),
(2, 'u', 0),
(2, 'e', 0),
(2, 'i', 1),
(3, 'a', 0),
(3, 'u', 1),
(3, 'e', 0),
(3, 'i', 0),
(4, 'a', 0),
(4, 'u', 0),
(4, 'e', 1),
(4, 'i', 0)
GO
INSERT INTO lessonCompleted(lessonID, userID) VALUES
(1, 1)
GO
SELECT * FROM [user];

--get first uncompleted lesson
--select a.lessonID from
--(select moocIndex, lessonID, lessonIndex from
--(select ID as moocID, [index] as moocIndex from mooc where courseID = 1) as a
--join
--(select moocID, ID as lessonID, [index] as lessonIndex from lesson) as b on a.moocID = b.moocID) a
--join
--(select lessonID from lessonCompleted where userID = 1) b on a.lessonID = b.lessonID
--order by moocIndex, lessonIndex;