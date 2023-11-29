CREATE TABLE IF NOT EXISTS friend(
    id INT AUTO_INCREMENT PRIMARY KEY,
    member1 INT,
    member2 INT,
    FOREIGN KEY (member1) REFERENCES member (id),
    FOREIGN KEY (member2) REFERENCES member (id)
);