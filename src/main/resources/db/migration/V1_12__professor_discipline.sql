CREATE TABLE IF NOT EXISTS `professor_discipline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_professor` bigint(20) NOT NULL,
  `id_discipline` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;