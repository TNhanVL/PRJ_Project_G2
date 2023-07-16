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
	certificateName text,
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
	content NTEXT NOT NULL,
	lessonID INT,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID)
);
GO

CREATE TABLE question (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	lessonID INT NOT NULL,
	[index] INT NOT NULL,
	content NTEXT NOT NULL,
    [type] INT NOT NULL,
	point INT,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID)
);
GO

CREATE TABLE answer (
	[ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	questionID INT NOT NULL,
	content NTEXT,
	correct BIT NOT NULL, --True: 1, False: 0
	FOREIGN KEY (questionID) REFERENCES question(ID)
);
GO

CREATE TABLE quizResult (
	ID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	lessonID INT NOT NULL,
	userID INT NOT NULL,
	startTime DATETIME,
	endTime DATETIME,
	FOREIGN KEY (lessonID) REFERENCES lesson(ID),
	FOREIGN KEY (userID) REFERENCES [user](ID)
);
GO

CREATE TABLE questionResult (
	quizResultID INT NOT NULL,
	questionID INT NOT NULL,
	selectedAnswer INT NOT NULL,
	FOREIGN KEY (quizResultID) REFERENCES quizResult(ID),
	FOREIGN KEY (questionID) REFERENCES question(ID),
	FOREIGN KEY (selectedAnswer) REFERENCES answer(ID),
	UNIQUE (quizResultID, questionID, selectedAnswer)
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
('a.jpg', 'dylan12', '0cc175b9c0f1b6a831c399e269772661', 'dylan@example.com', 'Huong', 'Nguyen Thi Diem', 0, '2003-10-12', 16, 1),
('a.jpg', 'diemhuong1210', '12345678', 'dh1210@example.com', 'Duong', 'Thanh', 1, '2003-10-10', 16, 1)
GO
INSERT INTO organization([name], logo, [description]) VALUES
('FPT University', 'FPT.png', N'Trường đại học top 1 Việt Nam');
GO
INSERT INTO lecturer(userID, organizationID) VALUES
(1, 1),
(2, 1)
GO
INSERT INTO course(title,[image],[description],organizationID,lecturerID,unSalePrice,price,rate) VALUES
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 1, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 2, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 1.2, 4.7),
('PYTHON FOR BEGINNER', 'a.png', 'ezsy', 1, 1, 500, 1.4, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 2.5, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 600, 4.7),
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 200, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 0.4, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 5, 4.7),
('Java basic', 'a.png', 'ezsy', 1, 1, 500, 3, 4.2),
('Java advance', 'a.png', 'medium difficult', 1, 2, 0, 2, 4.5),
('C++', 'a.png', 'hard', 1, 3, 800, 5, 4.7)
GO
INSERT INTO orderCourse(userID, courseID) VALUES
(1, 2), (1, 3)
GO
INSERT INTO purchasedCourse(userID, courseID) VALUES
(1, 1)
GO
INSERT INTO mooc(courseID, [index], title, [description]) VALUES
(1, 1, N'Hiragana 。ひらがな', ''),
(1, 2, N'Katakana 。dgがな', '')
GO
INSERT INTO lesson(moocID, title, [index], [type], [time]) VALUES
(1, 'Nihongo', 1, 0, 1),
(1, 'Nihongo', 2, 2, 5),
(2, 'Nihongo2', 1, 2, 5),
(2, 'Video', 2, 0, 5),
(2, 'Post', 3, 1, 5)
GO
INSERT INTO post(content, lessonID) VALUES
('baclieu.mp4', 1),
('baclieu.mp4', 4)
GO
INSERT INTO question(lessonID, [index], content, [type], point) VALUES
(2, 1, 'a.png', 0, 1),
(2, 2, 'i.png', 0, 1),
(2, 3, 'u.png', 1, 1),
(2, 4, 'e.png', 1, 1),
(2, 5, N'What is the character あ?', 10, 1)
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
(4, 'i', 0),
(5, 'a', 1),
(5, 'u', 0),
(5, 'e', 0),
(5, 'i', 0)
GO
--SELECT * FROM [user];

--get sum of completed lesson of a course
--select sum([time]) as sumTime from
--(select l.ID, [time] from
--(select * from lesson) as l
--join
--(select * from mooc where courseID = 1) as m
--on l.moocID = m.ID) l
--join
--(select * from lessonCompleted where userID = 1) lc
--on l.ID = lc.lessonID

-- Check if question are correct
--select 1 from
--(select selectedAnswer as ID from questionResult where quizResultID = 1 and questionID = 4) a
--full join
--(select ID from answer where questionID = 4 and correct = 1) b
--on a.ID = b.ID
--where a.ID is null or b.ID is null;

--select top 1 * from quizResult where userID = 1 and lessonID = 2 order by startTime desc;

--get number completed lesson of a mooc
--select count(*) as number from
--(select lessonID as ID from lessonCompleted where userID = 1) as a
--join
--(select ID from lesson where moocID = 1) as b
--on a.ID = b.ID;

--get last lessonID
--select top 1 lessonID from
--(select ID as moocID, [index] as moocIndex from mooc where courseID = 1) as a
--join
--(select moocID, ID as lessonID, [index] as lessonIndex from lesson) as b on a.moocID = b.moocID
--order by moocIndex desc, lessonIndex desc;

--get first uncompleted lessonID
--select top 1 lessonID from
--(select moocIndex, lessonID, lessonIndex from
--(select ID as moocID, [index] as moocIndex from mooc where courseID = 1) as a
--join
--(select moocID, ID as lessonID, [index] as lessonIndex from lesson) as b on a.moocID = b.moocID) a
--where lessonID not in
--(select lessonID from lessonCompleted where userID = 1)
--order by moocIndex, lessonIndex;

insert into post(lessonID, content) values
(5, N'<div class="tab-language" id="vn"><h3>Bài tập</h3>
    <p>Bạn hãy viết chương trình tạo ra 2 biến<span>&nbsp;</span><code>a</code><span>&nbsp;</span>và<span>&nbsp;</span><code>b</code>&nbsp;kiểu số nguyên sau đó gán giá trị cho<span>&nbsp;</span><code>a = 254</code><span>&nbsp;</span>và<span>&nbsp;</span><code>b = 343</code>&nbsp;và hiển thị ra màn hình thông tin sau:</p>
    <pre class=" language-markup"><code class=" language-markup">a + b = {P}</code></pre>
    <p>Với<span>&nbsp;</span><code>{P}</code><span>&nbsp;</span>là tổng của biến<span>&nbsp;</span><code>a</code><span>&nbsp;</span>và biến<span>&nbsp;</span><code>b</code>.</p>
    <h3>Lý thuyết</h3>
    <p>Khái niệm biến trong lập trình cũng giống khái niệm biến trong toán học, biến được dùng để đại diện cho một giá trị nào đó.</p>
    <p>Để khai báo biến kiểu số nguyên trong Java bạn sử dụng từ khóa <code>int</code> (<code>int</code> và viết tắt của <code>integer</code>) giống như sau:</p>
    <pre class=" language-cpp"><code class=" language-cpp"><span class="token keyword">int</span> tên_biến<span class="token punctuation">;</span></code></pre>
    <p>Ví dụ về chương trình tạo và sử dụng biến kiểu số nguyên:</p>
    <pre class=" language-java"><code class=" language-java"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">Variable</span> <span class="token punctuation">{</span>
        <span class="token keyword">public</span> <span class="token keyword">static</span> <span class="token keyword">void</span> <span class="token function">main</span><span class="token punctuation">(</span><span class="token class-name">String</span><span class="token punctuation">[</span><span class="token punctuation">]</span> args<span class="token punctuation">)</span> <span class="token punctuation">{</span>
            <span class="token comment">// Khai báo biến a kiểu số nguyên</span>
            <span class="token keyword">int</span> a<span class="token punctuation">;</span>
            <span class="token comment">// Gán giá trị cho a = 5</span>
            a <span class="token operator">=</span> <span class="token number">5</span><span class="token punctuation">;</span>
            <span class="token comment">// Hiển thị ra màn hình giá trị của biến a</span>
            <span class="token class-name">System</span><span class="token punctuation">.</span>out<span class="token punctuation">.</span><span class="token function">println</span><span class="token punctuation">(</span><span class="token string">"a = "</span> <span class="token operator">+</span> a<span class="token punctuation">)</span><span class="token punctuation">;</span>
        <span class="token punctuation">}</span>
    <span class="token punctuation">}</span></code></pre>
    <p>Kết quả khi chạy chương trình:</p>
    <pre class=" language-markup"><code class=" language-markup">a = 5</code></pre>
    <p>Bạn còn có thể vừa khai báo vừa gán giá trị cho biến trong 1 câu lệnh giống như chương trình sau:</p>
    <pre class=" language-java"><code class=" language-java"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">Variable</span> <span class="token punctuation">{</span>
        <span class="token keyword">public</span> <span class="token keyword">static</span> <span class="token keyword">void</span> <span class="token function">main</span><span class="token punctuation">(</span><span class="token class-name">String</span><span class="token punctuation">[</span><span class="token punctuation">]</span> args<span class="token punctuation">)</span> <span class="token punctuation">{</span>
            <span class="token comment">// Khai báo biến a kiểu số nguyên và gán giá trị cho a = 438</span>
            <span class="token keyword">int</span> a <span class="token operator">=</span> <span class="token number">438</span><span class="token punctuation">;</span>
            <span class="token comment">// Khai báo biến b kiểu số nguyên và gán giá trị cho b = 238</span>
            <span class="token keyword">int</span> b <span class="token operator">=</span> <span class="token number">238</span><span class="token punctuation">;</span>
            <span class="token comment">// Hiển thị ra màn hình hiệu của a và b</span>
            <span class="token class-name">System</span><span class="token punctuation">.</span>out<span class="token punctuation">.</span><span class="token function">println</span><span class="token punctuation">(</span><span class="token string">"a - b = "</span> <span class="token operator">+</span> <span class="token punctuation">(</span>a <span class="token operator">-</span> b<span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        <span class="token punctuation">}</span>
    <span class="token punctuation">}</span></code></pre>
    <p>Kết quả khi chạy chương trình:</p>
    <pre class=" language-markup"><code class=" language-markup">a - b = 200</code></pre>
    <p>Ngoài ra, bạn cũng có thể khai báo và gán giá trị cho nhiều biến trong 1 câu lệnh như giống như chương trình sau:</p>
    <pre class=" language-java"><code class=" language-java"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">Variable</span> <span class="token punctuation">{</span>
        <span class="token keyword">public</span> <span class="token keyword">static</span> <span class="token keyword">void</span> <span class="token function">main</span><span class="token punctuation">(</span><span class="token class-name">String</span><span class="token punctuation">[</span><span class="token punctuation">]</span> args<span class="token punctuation">)</span> <span class="token punctuation">{</span>
            <span class="token keyword">int</span> a <span class="token operator">=</span> <span class="token number">438</span><span class="token punctuation">,</span> b <span class="token operator">=</span> <span class="token number">238</span><span class="token punctuation">;</span>
            <span class="token class-name">System</span><span class="token punctuation">.</span>out<span class="token punctuation">.</span><span class="token function">println</span><span class="token punctuation">(</span><span class="token string">"a - b = "</span> <span class="token operator">+</span> <span class="token punctuation">(</span>a <span class="token operator">-</span> b<span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        <span class="token punctuation">}</span>
    <span class="token punctuation">}</span></code></pre>
    <p>Khi đặt tên cho biến bạn cần tuân theo 1 số nguyên tắc sau:</p>
    <ul>
    <li>Tên biến có thể có chữ cái, chữ số và dấu gạch dưới nhưng ký tự đầu tiên của tên biến bắt buộc phải là dấu gạch dưới hoặc chữ cái.
    <ul></ul>
    </li>
    <li>Tên biến không được có khoảng trắng.</li>
    <li>Tên biến không được trùng với các từ khóa như<span>&nbsp;</span><code>int, float, double, class, ...</code>&nbsp;(bạn có thể tham khảo thêm về các từ khóa trong Java <a href="https://codelearn.io/sharing/tong-hop-50-keyword-can-nho-trong-java">tại đây</a>)</li>
    </ul>
    <p>Một số tên biến hợp lệ:</p>
    <pre class=" language-cpp"><code class=" language-cpp"><span class="token keyword">int</span> a<span class="token punctuation">;</span>
    <span class="token keyword">int</span> _a<span class="token punctuation">;</span>
    <span class="token keyword">int</span> a10_<span class="token punctuation">;</span></code></pre>
    <p>Một số tên biến không hợp lệ:</p>
    <pre class=" language-cpp"><code class=" language-cpp"><span class="token keyword">int</span> <span class="token number">10</span>a<span class="token punctuation">;</span>
    <span class="token keyword">int</span> a <span class="token number">10</span><span class="token punctuation">;</span>
    <span class="token keyword">int</span> <span class="token keyword">int</span><span class="token punctuation">;</span>
    </code></pre>
    <p>Đọc tới đây bạn đã biết cách tạo và sử dụng biến trong Java, hãy quay lại phần bài tập và làm thử.</p>
    <p>Nếu bạn chưa làm được bài này thì có thể xem hướng dẫn ở bên dưới.</p>
    <h3>Hướng dẫn</h3>
    <p>Code mẫu:</p>
    <pre class=" language-java"><code class=" language-java"><span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">Variable</span> <span class="token punctuation">{</span>
        <span class="token keyword">public</span> <span class="token keyword">static</span> <span class="token keyword">void</span> <span class="token function">main</span><span class="token punctuation">(</span><span class="token class-name">String</span><span class="token punctuation">[</span><span class="token punctuation">]</span> args<span class="token punctuation">)</span> <span class="token punctuation">{</span>
            <span class="token keyword">int</span> a <span class="token operator">=</span> <span class="token number">254</span><span class="token punctuation">;</span>
            <span class="token keyword">int</span> b <span class="token operator">=</span> <span class="token number">343</span><span class="token punctuation">;</span>
            <span class="token class-name">System</span><span class="token punctuation">.</span>out<span class="token punctuation">.</span><span class="token function">println</span><span class="token punctuation">(</span><span class="token string">"a + b = "</span> <span class="token operator">+</span> <span class="token punctuation">(</span>a <span class="token operator">+</span> b<span class="token punctuation">)</span><span class="token punctuation">)</span><span class="token punctuation">;</span>
        <span class="token punctuation">}</span>
    <span class="token punctuation">}</span></code></pre></div>
    ');