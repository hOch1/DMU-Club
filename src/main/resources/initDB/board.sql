CREATE TABLE IF NOT EXISTS board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    createDate VARCHAR(255),
    member_id INT,
    FOREIGN KEY (member_id) REFERENCES member (id)
);