UPDATE personal_data pd
    JOIN employees c ON pd.personal_data_id = c.personal_data_id
    SET pd.name = COALESCE(NULLIF( ? , ''), pd.name),
        pd.lastname = COALESCE(NULLIF( ? , ''), pd.lastname),
        pd.dni = COALESCE(NULLIF( ? , ''), pd.dni),
        pd.birthday = COALESCE(NULLIF( ? , ''), pd.birthday),
        pd.nationality = COALESCE(NULLIF( ? , ''), pd.nationality),
        pd.cell_phone = COALESCE(NULLIF( ? , ''), pd.cell_phone),
        pd.email = COALESCE(NULLIF( ? , ''), pd.email)
WHERE c.id = ?;