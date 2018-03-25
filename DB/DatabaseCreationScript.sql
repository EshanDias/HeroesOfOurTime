CREATE TABLE Hero (
	HeroID INTEGER PRIMARY KEY AUTOINCREMENT
	, Name TEXT
	, Birthday DATETIME
	, Death DATETIME
	, Summary TEXT
	, Description TEXT
	, Comments TEXT
	, CreatedDate DATETIME
	, ModifiedDate DATETIME
	);

CREATE TABLE Login (
	ID INTEGER PRIMARY KEY AUTOINCREMENT
	, Name TEXT
	, Email TEXT
	, Username TEXT
	, Password TEXT
	, Hint TEXT
	, CreatedDate DATETIME
	, ModifiedDate DATETIME
	);
