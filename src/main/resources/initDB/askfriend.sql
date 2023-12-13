CREATE TABLE IF NOT EXISTS askfriend(
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_member INT,
    to_member INT,

    foreign key (from_member) references member(id),
    foreign key (to_member) references member(id)
);