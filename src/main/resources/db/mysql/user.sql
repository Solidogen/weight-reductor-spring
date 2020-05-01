CREATE DATABASE IF NOT EXISTS weightreductor;

ALTER DATABASE weightreductor
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON weightreductor.* TO 'weightreductor@%' IDENTIFIED BY 'weightreductor';
