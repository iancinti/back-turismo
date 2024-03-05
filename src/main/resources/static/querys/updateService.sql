UPDATE tourist_services s
SET s.description = COALESCE(NULLIF(?, ''), s.description),
    s.destination = COALESCE(NULLIF(?, ''), s.destination),
    s.date = COALESCE(NULLIF(?, ''), s.date),
    s.cost = COALESCE(NULLIF(?, ''), s.cost),
    s.pic = COALESCE(NULLIF(?, ''), s.pic)
WHERE s.code = ?;
