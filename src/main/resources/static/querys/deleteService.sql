UPDATE tourist_services
SET delete_at = NOW()
WHERE code = ?;