CREATE TABLE IF NOT EXISTS `response` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) NOT NULL,
  `description` varchar(1500) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `choice_id` bigint(20) NOT NULL,
  `professor_discipline_id` bigint(20) NOT NULL,
  `student_discipline_id` bigint(20) NOT NULL,
  `create_by` varchar(255) NOT NULL DEFAULT 'system_user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;