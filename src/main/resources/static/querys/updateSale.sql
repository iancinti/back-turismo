UPDATE sales s
SET s.payment_method = COALESCE(NULLIF(?, ''), s.payment_method),
    s.client_id = COALESCE(NULLIF(?, ''), s.client_id),
    s.package_id = COALESCE(NULLIF(?, ''), s.package_id),
    s.service_id = COALESCE(NULLIF(?, ''), s.service_id)
WHERE s.num_sale = ?;
