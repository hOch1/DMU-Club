CREATE TABLE IF NOT EXISTS chatlog(
    id INT,
    message TEXT,
    sendTime DATETIME DEFAULT NOW(),
    foreign key (id) references chat (id)
);