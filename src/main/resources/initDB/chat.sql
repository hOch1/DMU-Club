CREATE TABLE IF NOT EXISTS chat(
    id int AUTO_INCREMENT PRIMARY KEY,
    from_member int,
    to_member int,
    message TEXT,
    send_time TIMESTAMP,
    FOREIGN KEY (from_member) REFERENCES member (id),
    FOREIGN KEY (to_member) REFERENCES member (id)
);