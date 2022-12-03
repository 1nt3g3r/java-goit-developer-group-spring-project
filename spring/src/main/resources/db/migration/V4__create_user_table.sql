CREATE TABLE auth_user (
    user_id IDENTITY PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(100),
    enabled BOOL DEFAULT true
)