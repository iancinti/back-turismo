UPDATE sales
    SET delete_at = NOW()
WHERE num_sale = ?;