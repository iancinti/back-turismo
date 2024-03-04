UPDATE personal_data pd
    JOIN employees c ON pd.personal_data_id = c.personal_data_id
    SET pd.delete_at = NOW(),
        c.delete_at = NOW()
WHERE c.id = ?;
