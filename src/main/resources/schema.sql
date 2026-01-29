CREATE TABLE files (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    file_name VARCHAR(100),
    line_count INT,
    word_count INT
);