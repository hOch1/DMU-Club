CREATE TABLE IF NOT EXISTS report(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    solve BOOLEAN DEFAULT FALSE,
    member_id int,
    FOREIGN KEY (member_id) REFERENCES member (id)
);