USE starwars;

CREATE TABLE planet (
  planet_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  planet_name varchar(50) NOT NULL UNIQUE,
  planet_weather varchar(50) NOT NULL,
  planet_terrain varchar(100) NOT NULL,
  planet_views INTEGER DEFAULT 0,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_date TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
