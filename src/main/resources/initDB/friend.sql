CREATE TABLE IF NOT EXISTS friend(
    id INT AUTO_INCREMENT PRIMARY KEY,
    member1_id INT,
    member2_id INT,
    FOREIGN KEY (member1_id) REFERENCES member (id),
    FOREIGN KEY (member2_id) REFERENCES member (id)
);