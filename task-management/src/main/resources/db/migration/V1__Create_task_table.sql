-- Flyway migration script to create the `tasks` table

CREATE TABLE status (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        status_name VARCHAR(255) NOT NULL
);

CREATE TABLE tasks (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(255) NOT NULL,
                       status_id INT NOT NULL,
                       user_id INT NOT NULL,
                       priority_task TINYINT,
                       due_date TIMESTAMP,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (status_id) REFERENCES status(id)
);


