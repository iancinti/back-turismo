UPDATE personal_data pd
    JOIN clients c ON pd.personal_data_id = c.personal_data_id
    SET delete_at = NOW()
WHERE c.id = ?;