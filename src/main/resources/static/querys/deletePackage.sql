UPDATE tourist_package
SET delete_at = NOW()
WHERE code = ?;