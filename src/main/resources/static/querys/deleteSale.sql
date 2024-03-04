UPDATE sales
    SET delete_at = NOW()
WHERE id = ?;