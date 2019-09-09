SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `shamedb` ;
CREATE SCHEMA IF NOT EXISTS `shamedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `shamedb` ;

-- -----------------------------------------------------
-- Table `complex`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `complex` ;

CREATE TABLE IF NOT EXISTS `complex` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `street` VARCHAR(200) NULL,
  `city` VARCHAR(200) NULL,
  `state` CHAR(2) NULL,
  `zip` CHAR(5) NULL,
  `image_url` VARCHAR(2000) NULL,
  `num_units` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(400) NOT NULL,
  `user_profile_id` INT NULL,
  `role` VARCHAR(20) NULL,
  `enabled` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_user_profile1_idx` (`user_profile_id` ASC),
  CONSTRAINT `fk_user_user_profile1`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `user_profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_profile` ;

CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complex_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `email` VARCHAR(250) NULL,
  `first_name` VARCHAR(30) NULL,
  `last_name` VARCHAR(30) NULL,
  `display_name` VARCHAR(200) NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_profile_complex1_idx` (`complex_id` ASC),
  INDEX `fk_user_profile_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_profile_complex1`
    FOREIGN KEY (`complex_id`)
    REFERENCES `complex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_profile_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `complaint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `complaint` ;

CREATE TABLE IF NOT EXISTS `complaint` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complex_id` INT NOT NULL,
  `user_profile_id` INT NOT NULL,
  `title` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `is_resolved` TINYINT(1) NULL,
  `created_date` DATETIME NULL,
  `resolved_date` DATETIME NULL,
  `resolution_description` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_complaint_user_profile1_idx` (`user_profile_id` ASC),
  INDEX `fk_complaint_complex1_idx` (`complex_id` ASC),
  CONSTRAINT `fk_complaint_user_profile1`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `user_profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_complaint_complex1`
    FOREIGN KEY (`complex_id`)
    REFERENCES `complex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complaint_id` INT NOT NULL,
  `user_profile_id` INT NOT NULL,
  `text` TEXT NULL,
  `comment_date` DATETIME NULL,
  `vote` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_complaint1_idx` (`complaint_id` ASC),
  INDEX `fk_comment_user_profile1_idx` (`user_profile_id` ASC),
  CONSTRAINT `fk_comment_complaint1`
    FOREIGN KEY (`complaint_id`)
    REFERENCES `complaint` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user_profile1`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `user_profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `image` ;

CREATE TABLE IF NOT EXISTS `image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complaint_id` INT NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_image_complaint1_idx` (`complaint_id` ASC),
  CONSTRAINT `fk_image_complaint1`
    FOREIGN KEY (`complaint_id`)
    REFERENCES `complaint` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

CREATE TABLE IF NOT EXISTS `contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complex_id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  `phone` VARCHAR(25) NULL,
  `email` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_complex_idx` (`complex_id` ASC),
  CONSTRAINT `fk_contact_complex`
    FOREIGN KEY (`complex_id`)
    REFERENCES `complex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_complex_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_complex_rating` ;

CREATE TABLE IF NOT EXISTS `user_complex_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_profile_id` INT NOT NULL,
  `complex_id` INT NOT NULL,
  `comment` TEXT NULL,
  `last_update` DATETIME NULL,
  `rating` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_complex_rating_complex1_idx` (`complex_id` ASC),
  INDEX `fk_user_complex_rating_user_profile1_idx` (`user_profile_id` ASC),
  CONSTRAINT `fk_user_complex_rating_complex1`
    FOREIGN KEY (`complex_id`)
    REFERENCES `complex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_complex_rating_user_profile1`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `user_profile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO aptshamer@localhost;
 DROP USER aptshamer@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'aptshamer'@'localhost' IDENTIFIED BY 'aptshamer';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'aptshamer'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `complex`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (1, 'Creek Village Place', '2201 Arapahoe St', 'Denver', 'CO', '80205', 'https://sgcweb.s3.wasabisys.com/bdcnetwork/s3fs-public/styles/content_display_image/public/Indie%20Apartments%20copy.jpg?itok=5-cykb0O', 500);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (2, 'The Landings', '301 S Williams Street', 'Denver', 'CO', '80209', 'https://siouxfalls.business/wp-content/uploads/2017/07/IMG_Jul6201753752PM-1024x695.jpg', 200);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (3, 'The Commons', '282 S Logan Street', 'Denver', 'CO', '80209', 'https://ocbj.media.clients.ellingtoncms.com/img/photos/2018/06/05/Rimrock-Apartments_t670.jpg?b3f6a5d7692ccc373d56e40cf708e3fa67d9af9d', 40);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (4, 'The Uncommons', '691 W Hampden Avenue', 'Englewood', 'CO', '80110', 'https://cdn.vox-cdn.com/thumbor/u2VxfDNZGeYg_mtU8zaZOHN7czE=/0x0:1921x1081/1820x1213/filters:focal(808x388:1114x694):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/54665147/Broadway_and_Magnolia_apartments_1.0.jpg', 300);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (5, 'Village Place Homes', '598 W Kenyon Av', 'Englewood', 'CO', '80110', 'https://1bht2ni6oc-flywheel.netdna-ssl.com/wp-content/uploads/2018/03/o0bswKvrIrLgnHGh2Oqe5YuQqKwXUW2mrbyq3mU-d1Q-1024x682.jpg', 250);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (6, 'Lake Gardens Pointe', '12098 E Mississippi Ave', 'Aurora', 'CO', '80112', 'https://finance-commerce.com/files/2016/09/Jefferson-at-Plymouth2.jpg', 100);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (7, 'Oaks Crossing', '5600 S Parker Rd', 'Aurora', 'CO', '80015', 'https://images1.apartments.com/i2/HWeQEvtGdgkGVmxvrJcUaUyi9TfDh08HPizPPrtG3Mo/117/west-fourplex-homes-austin-tx-primary-photo.jpg', 4);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (8, 'Ridge Place Court', '6101 S Prescott', 'Littleton', 'CO', '80120', 'https://mhpmag.com/wp-content/uploads/2018/08/6203-09-N.-Ravenswood.jpg', 25);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (9, 'Creek Square Crossing', '398 W Caley Dr', 'Littleton', 'CO', '80120', 'https://milehighcre.com/wp-content/uploads/2018/09/Belle-Creek-750x375.jpg', 75);
INSERT INTO `complex` (`id`, `name`, `street`, `city`, `state`, `zip`, `image_url`, `num_units`) VALUES (10, 'Park Village Landing', '16550 Keystone Blvd', 'Parker', 'CO', '80134', 'https://bloximages.chicago2.vip.townnews.com/southernchestercountyweeklies.com/content/tncms/assets/v3/editorial/9/68/968140fa-8262-11e9-bd7c-e3ef4e4c3c0d/5cef0a30a1948.image.jpg?resize=1200%2C695', 190);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (1, 'testuser', 'testuser', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (2, 'Ryan', 'ryan', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (3, 'Dunwei', 'dunwei', NULL, 'admin', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (4, 'Hatle', 'hatle', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (5, 'Chana', 'chana', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (6, 'floridaman', 'florida', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (7, 'lumberjack', 'lumber', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (8, 'michigantransplant', 'trans', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (9, 'stoner', 'stoner', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (10, 'ultramarathon', 'ultra', NULL, 'user', true);
INSERT INTO `user` (`id`, `username`, `password`, `user_profile_id`, `role`, `enabled`) VALUES (11, 'zheng', '$2a$10$UJN8uo65fYMnSMbk5LC8Ju.0RFoMAHIsQBFE66CxGeSPmtutWWr.u', NULL, 'admin', true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_profile`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (1, 1, 1, 'test@email.com', 'test', 'user', 'testuser', 'http://www.google.com');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (2, 2, 2, 'coolpages@gmail.com', 'Cool', 'Pages', 'Randy', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSy1deWezmcXMbXewBhXO_3q7O7YgBKUM8O703t3pbA3LK--5ZU');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (3, 3, 3, 'sherlockholmes@hotmail.com', 'Sherlock', 'Holmes', 'Sherlock', 'https://previews.123rf.com/images/antonbrand/antonbrand1106/antonbrand110600069/9701517-cartoon-sherlock-holmes-with-a-magnifying-glass-isolated-on-white.jpg');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (4, 1, 4, 'gonefishin@gmail.com', 'Gone', 'Fishin', 'GoneFishin', 'https://cdn.shopify.com/s/files/1/0065/4917/6438/products/a-woman-fixes-a-chair-for-free-and-fishing-boat-on-the-lake-background_1080x.jpg?v=1540441143');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (5, 2, 5, 'psychfizz@yahoo.com', 'Psych', 'Fizz', 'PsychedelicFizzbuzz', 'https://www.clipartmax.com/png/middle/64-644163_female-woman-cartoon-avatar-%D0%BF%D0%BE%D0%B4%D0%BF%D0%B8%D1%81%D0%BA%D0%B0-%D0%BD%D0%B0-%D0%BA%D0%B0%D0%BD%D0%B0%D0%BB-gif.png');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (6, 4, 6, 'floridam@msn.com', 'Florida', 'Man', 'FloridaMan', 'https://s3-us-west-2.amazonaws.com/anchor-generated-image-bank/production/podcast_uploaded400/1900751/1900751-1559980116263-e0007c13041eb.jpg');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (7, 5, 7, 'lumberjack@gmail.com', 'Lumber', 'Jack', 'LumberJack', 'https://previews.123rf.com/images/kuliperko/kuliperko1803/kuliperko180300009/97202355-cartoon-character-avatar-symbol-lumberjack-holding-an-axe-vector-illustration.jpg');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (8, 8, 8, 'mitransplant@domain.com', 'Michigan', 'Dude', 'MITransplant', 'https://previews.123rf.com/images/yupiramos/yupiramos1709/yupiramos170910632/85494509-thief-dangerous-in-the-house-avatar-character-vector-illustration-design.jpg');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (9, 9, 9, 'therugtiestheroomtogether@gmail.com', 'Stoner', 'Stoner', 'Stoner', 'https://pickaface.net/gallery/avatar/hugobt205271488a31e87.png');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (10, 10, 10, 'iminbettershapethanyou@yahoo.com', 'Ultra', 'Marathon', 'LookAtMyMuscles', 'https://thumbs.dreamstime.com/z/muscle-man-icon-avatar-muscle-man-icon-over-white-background-vector-illustration-136106040.jpg');
INSERT INTO `user_profile` (`id`, `complex_id`, `user_id`, `email`, `first_name`, `last_name`, `display_name`, `image_url`) VALUES (11, 10, 11, 'imagenius@yahoo.com', 'Genius', 'Person', 'ImSmart', 'https://ahpinstitute.com/wp-content/uploads/2016/02/brain.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `complaint`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (1, 1, 1, 'Poop in the Elevator', 'This is gross.It\'s been here 3 days', false, '2019-02-12', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (2, 1, 2, 'Blocked Potty', 'The toilet is blocked and we cannot bathe the children until it is cleared. ', false, '2019-02-19', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (3, 2, 3, 'Flying Cockroaches', 'I have way too many roommates who pay no rent. They also fly, how gross is that?', false, '2019-03-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (4, 3, 4, 'Hair Clog', 'There\'s way too much hair in there and it backs up', false, '2019-03-08', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (5, 4, 5, 'Hot Plumber Needed', ' When I\'m in the shower I turn on the water and I get hot. Could some nice repairman fix my pipes so I don\'t always get hot? ', false, '2019-03-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (6, 5, 1, 'Smelly Man', 'This is to let you know there is a bad smell coming from the man next door.', false, '2019-03-16', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (7, 1, 1, 'Broken Window', 'I had a party and someone threw a vase at my head. I ducked, and the vase went through my living room window', false, '2018-09-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (8, 2, 2, 'Ceiling is Dripping', 'On the full moon, my ceiling drips and there are strange noises and howling cat sounds coming from the apartment above me', false, '2018-10-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (9, 3, 3, 'Moldy Parts', '50% of the walls are damp, 50% have crumbling plaster and\nthe rest are plain filthy.', false, '2018-11-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (10, 4, 4, 'Refrigerator Is Not Cool', 'My fridge keeps my food warm, which is the opposite of what I need to happen.', false, '2018-12-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (11, 5, 5, 'My Dryer Keeps My Clothes Cold and Wet', 'It\'s probably a nice feature, but not in this particular kind of machine', false, '2018-12-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (12, 6, 6, 'Parking Lot is Full of Glass', 'I\'ve had 2 flat tires already, could someone please clean up the parking lot? Drunk people smash bottles there every weekend', false, '2019-01-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (13, 6, 6, 'Animal Noises in the Walls', 'I\'d prefer not to think we have rats in the walls, but there is a lot of scratching and the sound of little pitter patter feet at night', false, '2019-01-20', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (14, 7, 7, 'Someone\'s Looking At Me', 'In the window. After dark. With a flashlight. It\'s creeping me out.', false, '2019-02-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (15, 7, 7, 'Leaky Pipes', 'In the bathroom there are waterstains in the wall. I\'d rather not think about it. Please fix it.', false, '2019-03-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (16, 8, 8, 'Gutter Fell Down', 'It\'s precariously hanging over my patio right now. It looks like it\'s going to fall on my head.', false, '2019-04-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (17, 8, 8, 'Tree Branch Broke My Car', 'There was a storm, and a tree branch fell on my car. There was a lot of damage. I need someone to take a look and pay for it.', false, '2019-05-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (18, 9, 9, 'Bird\'s Nest On My Door', 'I\'m not a baby bird killer, really! But I need to go in and out of my door, and the mom is mad at me when I get near her babies.', false, '2019-06-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (19, 9, 9, 'Other People Put Stuff On My Patio', 'Not trying to be mean, but I\'d rather not store other people\'s stuff on my patio. Could someone collect it, please?', false, '2019-07-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (20, 10, 10, 'The Rock Band Upstairs', 'I\'m sure they\'re great, but it\'s so loud, and I have to get up for work. Could they knock it off?', false, '2019-08-01', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (21, 10, 10, 'Hot Water Heater Not So Hot', 'I have to shower really fast, and then it\'s cold for half the day. I hate it.', false, '2019-01-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (22, 1, 1, 'Counter Tops Need Replacing', 'They\'re all scratched up from the prior resident.', false, '2019-02-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (23, 2, 2, 'Dog Poop on the Grass', 'It\'s petrifying. It may make a nice fossil some day, but I don\'t want to look at it on the way into my apartment.', false, '2019-03-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (24, 3, 3, 'Dog Poop on the Sidewalk', 'It doesn\'t seem to be washing off in the rain. Could someone collect it?', false, '2019-04-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (25, 4, 4, 'Howling Cats', 'Not the band. They\'re outside and have gang fights every night.', false, '2019-05-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (26, 5, 5, 'Got A Big Drip', 'Not my spouse. The kitchen fawcet won\'t stop dripping.', false, '2019-06-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (27, 6, 6, 'Awful Carpet', 'There\'s big brown stains in the middle, and I can\'t get them out. Do I have alien goo under my carpet?', false, '2019-07-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (28, 7, 7, 'Gutter Down', 'It\'s hanging across the main entryway to our building. It\'s very unsightly.', false, '2019-08-15', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (29, 8, 8, 'Roof Has Holes From The Tornado', 'I can see the sky. It\'s pretty and all, but I\'d rather do that when I\'m camping, not when I\'m at home.', false, '2019-03-25', NULL, NULL);
INSERT INTO `complaint` (`id`, `complex_id`, `user_profile_id`, `title`, `description`, `is_resolved`, `created_date`, `resolved_date`, `resolution_description`) VALUES (30, 9, 9, 'Creepy Guy In The Hallway', 'He sleeps there on and off, and I have to step over him to go to work.', false, '2019-04-25', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (1, 1, 2, 'he\'s right, still poop', '2019-02-25', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (2, 1, 1, 'at least it stopped smelling, now it\'s petrified', '2019-02-27', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (3, 1, 2, 'I\'ve invited my friends over to see it since they\'ve seen a lot of fossils in the area', '2019-03-03', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (4, 2, 3, 'my toilet is also blocked, but we usually bathe in the bathtub', '2019-02-28', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (5, 2, 4, 'strangely, my tub is backing up with brown water', '2019-03-05', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (6, 2, 5, 'at least the brown water isn\'t in your kitchen sink like mine', '2019-03-15', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (7, 3, 1, 'That is so gross!', '2019-03-05', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (8, 3, 2, 'I had that, but they fixed my place right away.', '2019-03-06', 1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (9, 4, 3, 'Buy some drano!', '2019-03-11', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (10, 4, 4, 'You could fix this for like 2 bucks', '2019-03-15', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (11, 5, 5, 'You might want to reword that one', '2019-03-21', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (12, 5, 6, 'I would love to get a hot plumber!', '2019-03-23', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (13, 6, 7, 'Where is your compassion? He can\'t help it!', '2019-03-26', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (14, 6, 8, 'The hallway by us has a smell too', '2019-03-30', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (15, 7, 9, 'That was YOUR party? You need to keep it down!', '2018-09-03', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (16, 7, 10, 'The whole place is noisy, and the walls are thin.', '2018-09-04', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (17, 8, 1, 'Beware the Jabberwock, my son! The jaws that bite, the claws that catch!', '2018-10-03', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (18, 8, 2, 'Dude, you need to move. This could get ugly.', '2018-10-06', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (19, 9, 3, 'If you have moldy parts, you should see a doctor.', '2018-11-05', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (20, 9, 4, 'So gross! Mine is worse, though.', '2018-11-10', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (21, 10, 5, 'My whole place is cold and wet', '2018-12-12', -1);
INSERT INTO `comment` (`id`, `complaint_id`, `user_profile_id`, `text`, `comment_date`, `vote`) VALUES (22, 10, 6, 'I got lucky and they replaced my dryer. My washer leaks, though.', '2018-12-17', -1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (1, 1, 'https://www.sandatlas.org/wp-content/uploads/Arkose-00184.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (2, 1, 'http://www.thefossilforum.com/uploads/monthly_03_2010/post-1836-12677677858118_thumb.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (3, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRP1zb7LqZNPVCu1HhZZZyMYfQ5hXLaUsRoW_wHqqVucVfRZeWO');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (4, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqnoqTq8VznIFC-Op5vUTLo0kfYX04yBghXZxQDYyxz6swVrTM2g');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (5, 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSreE1aXQLODjDv3KqTfV3TBJMG4q7vFQ0zM-afQ2ZJQHFRJFh3');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (6, 3, 'https://askentomologists.files.wordpress.com/2015/07/306014_4882687660226_1530880296_n.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (7, 4, 'https://www.kewforestplumbing.com/wp-content/uploads/clogged-drain-hair-clog.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (8, 4, 'https://bernerwise.com/wp-content/uploads/2015/01/ballofhair.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (9, 5, 'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/3eff8fa6-673b-499a-add0-3e114e82bc4e/duvbez-bf539f99-9099-4a9f-98b6-9307bcbc7123.jpg/v1/fill/w_1032,h_774,q_70,strp/steamy_shower__by_bitchinvixen_duvbez-pre.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9OTYwIiwicGF0aCI6IlwvZlwvM2VmZjhmYTYtNjczYi00OTlhLWFkZDAtM2UxMTRlODJiYzRlXC9kdXZiZXotYmY1MzlmOTktOTA5OS00YTlmLTk4YjYtOTMwN2JjYmM3MTIzLmpwZyIsIndpZHRoIjoiPD0xMjgwIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmltYWdlLm9wZXJhdGlvbnMiXX0.SBkx6nnsytUgjkag1xKFo5dcPDu9w6XeqTIQBYs8pKE');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (10, 5, 'https://external-preview.redd.it/Z4VCHMi3dolQQt6GgJPZI1AfxGVSetTX-p5etUK2h-0.jpg?width=640&crop=smart&auto=webp&s=9fd04caec039155dd33ac2ab2f2258b7a24b1f8d');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (11, 6, 'http://www.mypecs.com/ImageServer/ImageService.svc/GetPecsCardImage/70,359');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (12, 6, 'https://cbsnews1.cbsistatic.com/hub/i/2012/12/18/1cc8896f-d258-11e2-a43e-02911869d855/bad-smell-stinks-640.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (13, 7, 'https://media.wavy.com/nxs-wavytv-media-us-east-1/photo/2017/05/15/broken-windows-durham-apartment-girl-shot_37720012_ver1.0_1280_720.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (14, 7, 'http://www.majorgeeks.com/news/file/6683_brokenwindow.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (15, 8, 'https://i.pinimg.com/originals/4d/46/88/4d4688f458f9ab7466d8bca01eb9d708.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (16, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1i7RXh4k0PNuMgcojTQ8TC9FZx92rdqxf1pmL4xbr8Mv6UeIs');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (17, 9, 'https://www.howtoremoveblackmold.com/wp-content/uploads/2017/05/Mold-on-the-Walls.jpg');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (18, 9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLc8jYbpSq6PP1yvUA9RTj9MhifHjcWX_peu1nCxTvd6VSYEqj');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (19, 10, 'http://s.fsdn.com/sd/articles/09/05/13/1536245-1-thumblg.png');
INSERT INTO `image` (`id`, `complaint_id`, `image_url`) VALUES (20, 10, 'https://image.dynamixse.com/s/resize/250x315/http://cdn.dynamixse.com/thehomefixitpagecom/thehomefixitpagecom_562049183.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `contact`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (1, 1, 'Manager', '312-555-5555', 'manager@apartment.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (2, 2, 'Manager', '303-555-1111', 'manager@thelandings.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (3, 2, 'Leasing Agent', '303-555-2222', 'info@thecommons.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (4, 3, 'Maintenance', '303-555-3333', 'maintenance@theuncommons.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (5, 4, 'Grounds', '303-555-4444', 'grounds@home.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (6, 5, 'Leasing Agent', '720-555-1111', 'leasing@villageplace.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (7, 6, 'Info', '720-555-1234', 'rentme@lakegardens.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (8, 7, 'Manager', '720-555-2222', 'manager@oakscrossing.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (9, 8, 'Info', '303-555-1234', 'info@ridgeplacecourt.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (10, 9, 'Sales', '303-555-5432', 'sales@creeksquarecrossing.com');
INSERT INTO `contact` (`id`, `complex_id`, `name`, `phone`, `email`) VALUES (11, 10, 'Manager', '303-555-4321', 'info@parkvillagelanding.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_complex_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `shamedb`;
INSERT INTO `user_complex_rating` (`id`, `user_profile_id`, `complex_id`, `comment`, `last_update`, `rating`) VALUES (1, 1, 1, 'what a great place', '2019/08/15', 5);
INSERT INTO `user_complex_rating` (`id`, `user_profile_id`, `complex_id`, `comment`, `last_update`, `rating`) VALUES (2, 1, 1, 'Wow, the siding is so dirty and moldy', '2019/02/10', 3);
INSERT INTO `user_complex_rating` (`id`, `user_profile_id`, `complex_id`, `comment`, `last_update`, `rating`) VALUES (3, 2, 2, 'There are so many bugs in my apartment, I feel ike I have multiple roommates', '2019/03/15', 3);
INSERT INTO `user_complex_rating` (`id`, `user_profile_id`, `complex_id`, `comment`, `last_update`, `rating`) VALUES (4, 3, 3, 'This place smells like weed all the time', '2019/04/20', 5);
INSERT INTO `user_complex_rating` (`id`, `user_profile_id`, `complex_id`, `comment`, `last_update`, `rating`) VALUES (5, 4, 4, 'My neighbors blast music, and it\'s not even good music. Management never responds to complaints', '2019/05/16', 2);
INSERT INTO `user_complex_rating` (`id`, `user_profile_id`, `complex_id`, `comment`, `last_update`, `rating`) VALUES (6, 5, 5, 'Maintenance trucks always block the driveway', '2019/06/25', 4);

COMMIT;


START TRANSACTION;
USE `shamedb`;
UPDATE user SET user_profile_id = 1 WHERE id = 1;
UPDATE user SET user_profile_id = 2 WHERE id = 2;
UPDATE user SET user_profile_id = 3 WHERE id = 3;
UPDATE user SET user_profile_id = 4 WHERE id = 4;
UPDATE user SET user_profile_id = 5 WHERE id = 5;
UPDATE user SET user_profile_id = 6 WHERE id = 6;
UPDATE user SET user_profile_id = 7 WHERE id = 7;
UPDATE user SET user_profile_id = 8 WHERE id = 8;
UPDATE user SET user_profile_id = 9 WHERE id = 9;
UPDATE user SET user_profile_id = 10 WHERE id = 10;
UPDATE user SET user_profile_id = 11 WHERE id = 11;

COMMIT;

