DROP DATABASE IF EXISTS TestBlogDB;
CREATE DATABASE TestBlogDB;

USE TestBlogDB;

--
-- Table structure for table `authors`
--
CREATE TABLE IF NOT EXISTS `authors` (
	`author_id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(20) NOT NULL,
	`password` varchar(20) NOT NULL,
	`enabled` tinyint(1) NOT NULL,
	PRIMARY KEY (`author_id`),
	KEY `username` (`username`)
);
--
-- Dumping data for table `authors`
--
INSERT INTO `authors` (`author_id`, `username`, `password`, `enabled`) 
				 VALUES (1, "admin", "password", 1),
						(2, "user", "password", 1),
						(3, "both", "password", 1),
						(4, "neither", "password", 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(20) NOT NULL,
 `authority` varchar(20) NOT NULL,
 KEY `username` (`username`)
);
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('user', 'ROLE_USER'),
('both', 'ROLE_ADMIN'),
('both', 'ROLE_USER'),
('neither', 'EATS_POTATOES');

--
-- Table structure for table 'blogs'
-- Removed tag_id becuase we are going to use blog_id as the foriegn key

CREATE TABLE IF NOT EXISTS `blogs` (
`blog_id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(50) NOT NULL,
`content` text,
`author_id` int(11),
`date` date,
`category_id` int(11),
`image` blob,
`published` boolean,
PRIMARY KEY (`blog_id`)
);

--
-- Dumping data for table 'blogs'
-- 

INSERT INTO `blogs` (`blog_id`, `title`, `content`, `author_id`, `date`, `category_id`, `image`, `published`) VALUES 

(1, 'Dayman, Fighter of the Nightman', 'Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead. <br> <img src="https://probaway.files.wordpress.com/2013/07/sartre_22.jpg" alt="sartre" width="530" height="531" data-mce-src="https://probaway.files.wordpress.com/2013/07/sartre_22.jpg">', 1, '2014-01-01', 1, 'someurl', true),
(2, 'Is There an Absurdism Ipsum?', 'This is the second blog', 1, '2014-05-21', 2, 'someurl', false),
(3, 'Craft Mac N Sneeze', 'Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella. <br> <img src="https://i.ytimg.com/vi/QwoghxwETng/maxresdefault.jpg" alt="" width="420" height="236" data-mce-selected="1">', 1, '2014-10-17', 2, 'someurl', true),
(4, 'Sweet Dee Has A Heart Attack', 'imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro.<br> <img src="http://www.slate.com/content/dam/slate/blogs/browbeat/2014/06/02/slavoj_zizek_calls_students_stupid_and_boring_stop_worshiping_this_man_video/154445485-slavoj-zizek-attends-the-premiere-of-the-perverts-guide.jpg.CROP.promovar-medium2.jpg" alt="" width="354" height="486" data-mce-selected="1">', 1, '2015-04-12', 3, 'someurl', true),
(5, 'CharDee MacDennis', 'De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. ', 1, '2015-07-09', 1, 'someurl', true),
(6, "Frank's Pocket Sausage", 'Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby.', 1, '2015-08-15', 4, 'someurl', true),
(7, 'Soda Can Full of Wine', 'Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. ', 1, '2015-06-14', 1, 'someurl', true),
(8, 'Blogzz for Dayzzz', 'Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. ', 1, '2015-11-12', 1, 'someurl', false),
(9, 'Deeze Blogs Be Crazy!', 'Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro.', 1, '2016-02-28', 3, 'someurl', true),
(10, 'Who Dat? Bloggy MacBlawgz', 'Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus', 1, '2016-03-30', 4, 'someurl', true),
(11, 'I Got Dis Blog Right Here!', 'evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carn', 1, '2014-07-11', 1, 'someurl', false),
(12, 'Back At It Again With The Mighty Blizogs', 'evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carn', 1, '2016-11-18', 3, 'someurl', true),
(13, 'In A Simulation', 'evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carn', 1, '2016-12-02', 4, 'someurl', false);


--
-- Table structure for 'tags'
--

CREATE TABLE IF NOT EXISTS `tags`(
`tag_id` int(11) NOT NULL AUTO_INCREMENT,
`tag` varchar(50) NOT NULL UNIQUE,
PRIMARY KEY (`tag_id`)
);

--
-- Dumping data for table 'tags'
--

INSERT INTO `tags` (`tag_id`, `tag`) VALUES

(1, '#storm'),
(2, '#extreme'),
(3, '#dennis'),
(4, '#sweetdee'),
(5, '#mac'),
(6, '#charlie'),
(7, '#frank'),
(8, '#chill'),
(9, '#crazy'),
(10, '#radtastic'),
(11, '#cool'),
(12, '#legit');


--
-- Table Structure for table 'blogs_tags'
--

CREATE TABLE IF NOT EXISTS `blogs_tags` (
`blog_id` int(11) NOT NULL,
`tag_id` int(11) NOT NULL
);

--
-- Dumping data for table 'tags'
--

INSERT INTO `blogs_tags` (`blog_id`, `tag_id`) VALUES
(1, 2),
(1, 4),
(2, 1),
(2, 3),
(2, 5),
(3, 5),
(4, 4),
(4, 3),
(4, 2),
(5, 9),
(5, 12),
(5, 10),
(6, 3),
(6, 4),
(7, 3),
(8, 6),
(8, 8),
(9, 6),
(9, 11),
(9, 3),
(10, 10),
(10, 11),
(10, 12),
(11, 1),
(11, 8),
(11, 5),
(12, 2),
(12, 3),
(13, 9),
(13, 6),
(13, 4);


--
-- Table structure for table 'categories'
--

CREATE TABLE IF NOT EXISTS `categories` (
`category_id` int(11) NOT NULL AUTO_INCREMENT,
`category` varchar(25) UNIQUE,
PRIMARY KEY (`category_id`)
);

--
-- Dumping data for table 'categories'
-- 

INSERT INTO `categories` (`category_id`, `category`) VALUES
(1, 'Events'),
(2, 'Philanthropy'),
(3, 'Storms'),
(4, 'Highlights');


--
-- Table structure for table 'pages'
--

CREATE TABLE IF NOT EXISTS `pages` (
`page_id` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(50) NOT NULL,
`content` text,
`image` blob,
PRIMARY KEY (`page_id`)
);

--
-- Dumping data for table 'pages'
-- 

INSERT INTO `pages` (`page_id`, `title`, `content`, `image`) VALUES
(1, 'Fire Twister Escape', 'There is a baby-eating fire twister outside of Kansas City', 'someurl'),
(2, 'Sharknado', 'There is a baby-eating sharknado outside of Atlanta', 'someurl'),
(3, 'Tsunami Weekend', 'I will be taking the jet out for a weekend during a projected tsunami to help save lives. We will be dropping in on surf boards to save baby dolphins for getting too close to hazardous human civilaztions.', 'someurl'),
(4, 'Potato Tornado', 'Sometimes, there are tornados. And sometimes, they spend too much time in potato farms. But it is not necessarily a bad thing because everybody gets free french fries for weeks after.', 'someurl');


--
-- Constraints for table `authorities`, `blogs`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `authors` (`username`);

ALTER TABLE `blogs`
 ADD CONSTRAINT `blogs_authors_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`author_id`);
 -- ADD CONSTRAINT `blogs_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

ALTER TABLE `blogs`
 ADD CONSTRAINT `blogs_categories_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

ALTER TABLE `blogs_tags`
 ADD UNIQUE `blogs_tags_unique` (`blog_id`, `tag_id`)