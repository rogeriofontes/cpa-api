CREATE TABLE IF NOT EXISTS `professor_discipline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `professor_id` bigint(20) NOT NULL,
  `discipline_id` bigint(20) NOT NULL,
  `create_by` varchar(255) NOT NULL DEFAULT 'system_user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;