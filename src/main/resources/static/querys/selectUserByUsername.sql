SELECT u.username, u.password, a.authority
FROM users u
         JOIN authorities a ON u.username = a.username
WHERE u.username = ?;