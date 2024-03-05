UPDATE tourist_package s
SET s.name = COALESCE(NULLIF(?, ''), s.name),
    s.destination = COALESCE(NULLIF(?, ''), s.destination),
    s.cost = COALESCE(NULLIF(?, ''), s.cost),
    s.pic = COALESCE(NULLIF(?, ''), s.pic)
WHERE s.code = ?;
