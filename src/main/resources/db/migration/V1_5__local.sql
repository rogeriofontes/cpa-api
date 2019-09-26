CREATE TABLE IF NOT EXISTS `local` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(150) NOT NULL,
  `uf` varchar(150) NOT NULL,
  `create_by` varchar(255) NOT NULL DEFAULT 'system_user',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255),
  `last_modified_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO `local` (`id`,`city`,`uf`,`create_by`,`created_date`,`last_modified_by`,`last_modified_date`) VALUES (1,'Uberlândia','MG','root@localhost','2001-01-01','root@localhost','2001-01-01');
