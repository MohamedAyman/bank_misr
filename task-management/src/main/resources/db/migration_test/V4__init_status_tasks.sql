-- Inserting statuses
INSERT INTO status (status_name)
VALUES
    ('TODO'),
    ('IN_PROGRESS'),
    ('DONE');

-- Inserting tasks
INSERT INTO tasks (title, description, status_id, user_id, priority_task, due_date)
VALUES
    ('Task 1', 'Task Description', 1, 1, 0, CURRENT_TIMESTAMP),
    ('Task 2', 'Task Description', 2, 2, 0, CURRENT_TIMESTAMP),
    ('Task 3', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 4', 'Task Description', 3, 2, 0, CURRENT_TIMESTAMP),
    ('Task 5', 'Task Description', 2, 2, 0, CURRENT_TIMESTAMP),
    ('Task 6', 'Task Description', 3, 2, 0, CURRENT_TIMESTAMP),
    ('Task 7', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 8', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 9', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 10', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 11', 'Task Description', 3, 2, 0, CURRENT_TIMESTAMP),
    ('Task 12', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 13', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 14', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 15', 'Task Description', 2, 2, 0, CURRENT_TIMESTAMP),
    ('Task 16', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 17', 'Task Description', 3, 2, 0, CURRENT_TIMESTAMP),
    ('Task 18', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 19', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP),
    ('Task 20', 'Task Description', 2, 2, 0, CURRENT_TIMESTAMP),
    ('Task 21', 'Task Description', 1, 2, 0, CURRENT_TIMESTAMP);
