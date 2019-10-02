CREATE TABLE IF NOT EXISTS student_discipline(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_student` bigint(20) NOT NULL,
  `id_discipline` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;