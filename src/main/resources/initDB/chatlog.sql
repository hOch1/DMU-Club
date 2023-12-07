CREATE TABLE IF NOT EXISTS chatlog(
    id INT AUTO_INCREMENT PRIMARY KEY,
    chat_id INT,
    message TEXT,
    sendTime DATETIME DEFAULT NOW(),
    foreign key (chat_id) references chat (id)
);