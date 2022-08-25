
CREATE TABLE `mining_usr` (
  `usr_idx` int(5) auto_increment,
  `usr_addr` varchar(64) NOT NULL COMMENT '유저지갑주소',
  `reg_dt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  PRIMARY KEY (`usr_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='회원정보'

CREATE TABLE `vgn_history` (
  `vhis_idx` int(11) auto_increment,
  `usr_addr` varchar(64) NOT NULL COMMENT '유저지갑주소',
  `usr_vgncnt` varchar(64) NOT NULL COMMENT 'vgn토큰',
  `reg_dt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  PRIMARY KEY (`vhis_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='VGN이력'

CREATE TABLE `pfru_history` (
  `phis_idx` int(11) auto_increment,
  `usr_addr` varchar(64) NOT NULL COMMENT '유저지갑주소',
  `usr_pfrucnt` varchar(64) NOT NULL COMMENT '프루타',
  `tx_hash` varchar(64) NOT NULL COMMENT '트랙젝션',
  `reg_dt` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  PRIMARY KEY (`phis_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='PFRUTA이력'