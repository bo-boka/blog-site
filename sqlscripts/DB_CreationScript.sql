DROP DATABASE IF EXISTS BlogDB;
CREATE DATABASE BlogDB;

USE BlogDB;

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
						(2, "worker", "password", 1),
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
('worker', 'ROLE_WORKER'),
('both', 'ROLE_ADMIN'),
('both', 'ROLE_WORKER'),
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

(1, 'Dayman, Fighter of the Nightman','Venture? Circumnavigated another world. A very small stage in a vast cosmic arena. Light years. Globular star cluster are creatures of the cosmos, the only home we''ve ever known rich in heavy atoms Sea of Tranquility two ghostly white figures in coveralls and helmets are soflty dancing Drake Equation with pretty stories for which there''s little good evidence, circumnavigated, Orion''s sword consciousness extraordinary claims require extraordinary evidence. Inconspicuous motes of rock and gas. As a patch of light from which we spring the ash of stellar alchemy, stirred by starlight! Radio telescope, Sea of Tranquility from which we spring shores of the cosmic ocean, the carbon in our apple pies bits of moving fluff trillion radio telescope a mote of dust suspended in a sunbeam consciousness. Circumnavigated. Orion''s sword paroxysm of global death culture, ship of the imagination! Paroxysm of global death a billion trillion! Birth courage of our questions vanquish the impossible, citizens of distant epochs the sky calls to us consciousness how far away, decipherment Jean-François Champollion are creatures of the cosmos how far away colonies, courage of our questions. Two ghostly white figures in coveralls and helmets are soflty dancing, billions upon billions, vastness is bearable only through love concept of the number one great turbulent clouds citizens of distant epochs. Radio telescope dream of the mind''s eye Euclid galaxies finite but unbounded. <br><img src="https://i.ytimg.com/vi/6h8043y-PwI/maxresdefault.jpg" alt="" width="550" height="344">', 1, '2016-11-18', 3, 'someurl', true), 
(2, 'Is There an Absurdism Ipsum?', 'This is the second blog', 1, '2014-05-21', 2, 'someurl', false),
(3, 'Craft Mac N Sneeze', 'Cat in the wall Strap on my job helmet Salt the snail spaghetti day getting weird squeeze down into a job cannon youre a master of Frank. Charlie work sun Frank Frank Rum Ham Charlie Kelly, Wild Card seabird Cat in the wall Cat in the wall Cat in the wall rock. Seabird sun and friendship for everyone rock Bird Frank, Wine in a can magnum condoms rock karate grain alcohol baby. Charlie Kelly and friendship for everyone Rum Ham getting weird seabird Frank Wine in a can, Frank Wild Card Cat in the wall sun Wild Card Wine in a can. And friendship for everyone Wine in a can sun and friendship for everyone Strap on my job helmet, the DENNIS system dayman Charlie Kelly Wild Card Charlie Kelly nightman spaghetti day. Wine in a can youre a master of magnum condoms spaghetti day, Strap on my job helmet Cat in the wall spaghetti day flag seabird getting weird spaghetti day. The DENNIS system nightman Salt the snail, Cat in the wall the DENNIS system karate Cat in the wall squeeze down into a job cannon Cat in the wall dayman squeeze down into a job cannon seabird. Champion of the the DENNIS system Wine in a can Charlie Kelly wad of hundreds fire off into job land champion of the troll toll, Bird magnum condoms Charlie work Charlie work. Wine in a can Charlie Kelly Charlie work Cat in the wall squeeze down into a job cannon Sweet Dee grain alcohol baby, Cat in the wall Cat in the wall seabird troll toll Wine in a can. <br> <img src="https://i.ytimg.com/vi/EinzBoVnmRs/maxresdefault.jpg" alt="" width="550" height="366">', 1, '2015-08-17', 2, 'someurl', true),
(4, 'Sweet Dee Has A Heart Attack', 'imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro.<br> <img src="http://www.bobbledybooks.com/assets/Big-Island-Day-trip-800x500.jpg" alt="" width="550" height="344">', 1, '2015-06-29', 3, 'someurl', true),
(5, 'CharDee MacDennis', 'Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead. <br> <img src="https://i.ytimg.com/vi/QwoghxwETng/maxresdefault.jpg" alt="" width="550" height="309">', 1, '2014-07-30', 1, 'someurl', true),
(6, 'Gotta Pay the Troll Toll', 'Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby.<br> <img src="http://2014hoaxblog.s3.amazonaws.com/2014lighttree01.jpg" alt="" width="550" height="365">', 1, '2015-08-15', 4, 'someurl', true),
(7, 'Soda Can Full of Wine', 'Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit??, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus<br> <img src="http://marcelstotalfitness.com/wp-content/uploads/2014/07/1294-photography_sunset_az_monsoon_wallpaper.jpg" alt="" width="550" height="368">', 1, '2016-03-30', 4, 'someurl', true),
(8, 'Blogzz for Dayzzz', 'Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. ', 1, '2015-11-12', 1, 'someurl', false),
(9, 'Nobody Likes Salting the Snail!', 'The good guys are always stalwart and true, the bad guys are easily distinguished by their pointy horns or black hats, and, uh, we always defeat them and save the day. No one ever dies, and everybody lives happily ever after. You''re gonna die screaming but you won''t be heard. Stay away from hyena people, or any loser athletes, or if you see anyone who''s invisible. So I skipped intro to evil or whatever, but how is it that I get an F, when this guy that we''re reading, Chauncey, can''t even spell? We''ll have to call it early quantum state phenomenon.<br><img src="http://www.interestingfactsfun.com/wp-content/uploads/2016/02/Mega-Tsunami.jpeg" alt="" width="550" height="398">', 1, '2016-02-28', 3, 'someurl', true),
(10, 'Who Dat? Bloggy MacBlawgz', 'Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. <br><img src="http://t08.deviantart.net/dycRA2iBqKyztLVg317tps1nucw=/fit-in/700x350/filters:fixed_height(100,100):origin()/pre15/fa01/th/pre/f/2016/151/8/0/sweet_dee_passed_out_by_jadedownthedrain-da4jy91.jpg" alt="" width="457" height="350">', 1, '2015-06-14', 1, 'someurl', true),
(11, 'I Got Dis Blog Right Here!', 'evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carn', 1, '2014-07-11', 1, 'someurl', false),
(12, 'Back At It Again With The Mighty Blizogs', 'De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. <br><img src="http://cdn1.matadornetwork.com/blogs/1/2012/08/lakeshore.png" alt="" width="550" height="367">', 1, '2015-07-09', 1, 'someurl', true),
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
(1, 'Fire Twister Escape', 'For those who have seen the Earth from space, and for the hundreds and perhaps thousands more who will, the experience most certainly changes your perspective. The things that we share in our world are far more valuable than those which divide us. The path of a cosmonaut is not an easy, triumphant march to glory. You have to get to know the meaning not just of joy but also of grief, before being allowed in the spacecraft cabin. Science has not yet mastered prophecy. We predict too much for the next year and yet far too little for the next 10. Spaceflights cannot be stopped. This is not the work of any one man or even a group of men. It is a historical process which mankind is carrying out in accordance with the natural laws of human development. The dreams of yesterday are the hopes of today and the reality of tomorrow.<br> <img src="http://cdn.desktopwallpapers4.me/wallpapers/aircraft/1920x1080/3/23871-lockheed-sr-71-blackbird-1920x1080-aircraft-wallpaper.jpg" alt="" width="1100" height="619">', 'someurl'),
(2, 'Sharknado', 'The human mind is like Van Halen; if you just pull out one piece and keep replacing it, it just degenerates. Well we could grind our enemies into talcum powder with a sledgehammer but, gosh, we did that last night. It eats you, starting with your bottom. Any self-respecting demon should be living in a pit of filth or a nice crypt. We''re old friends from Navy. Friends from Old Navy. I worked retail, he''d come in, buy slacks Say! look at you! You look just like me! We''re very pretty. Say, aren''t you leaving a hole in the middle of some soggy group hug? Apocalypse, we''ve all been there; the same old trips, why should we care? My Uncle Rory was the stodgiest taxidermist you''ve ever met by day. Sweetie, if he had a tussle with that Sasquatch, we''d be in the dirt right about now, scooping up the Captain''s teeth. Occasionally, I''m callous and strange. Anything for you, because I love you. Deep, deep man love. You were a worthless being before you were ever a monster. Why not default them with ninja skills or whatever? Okay, uh, I''m lost. I''m angry. And I''m armed. I''m a rogue demon hunter now. I told him that I loved him, I kissed him, and I killed him. I wanna hurt you, but I can''t resist the sinister attraction of your cold and muscular body! It''s my estimation that every man ever got a statue made of him was one kind of sommbitch or another.<br> <img src="http://www.cheatsheet.com/wp-content/uploads/2014/07/sharknado-2-the-second-one-new-york-city-syfy.jpg" alt="" width="880" height="494">', 'someurl'),
(3, 'Tsunami Weekend', 'You probably haven''t heard of them tilde cold-pressed selfies aesthetic. Vape aesthetic forage, selvage whatever retro hexagon bicycle rights chambray hell of jean shorts flannel letterpress chartreuse. Vaporware before they sold out ennui, fap yr authentic +1 shoreditch shabby chic dreamcatcher pabst asymmetrical quinoa. Cray twee raclette, tilde paleo put a bird on it offal. Pabst pok pok kogi bitters, sartorial truffaut pinterest before they sold out viral tousled snackwave. Lo-fi vape normcore biodiesel succulents freegan. PBR&B chambray offal, messenger bag disrupt yuccie cray tattooed plaid pok pok forage. Post-ironic hammock hexagon, try-hard pabst PBR&B sustainable keffiyeh health goth squid tattooed kombucha. Wolf brunch four dollar toast, umami poke chambray actually drinking vinegar portland pork belly. Before they sold out blue bottle sustainable, bespoke tilde raclette aesthetic swag etsy food truck 90''s activated charcoal readymade la croix. Master cleanse retro pabst raclette chambray, chia lo-fi lyft succulents jianbing crucifix hexagon listicle banjo. Thundercats pug subway tile, vaporware literally schlitz hella selvage affogato hexagon roof party etsy. Fanny pack listicle PBR&B, 3 wolf moon crucifix asymmetrical yr. Salvia hashtag glossier plaid. Street art intelligentsia gochujang disrupt blog. Humblebrag brooklyn chillwave, +1 pinterest pickled pok pok butcher quinoa tilde deep v kombucha. Man bun tumblr blue bottle vexillologist williamsburg, subway tile kogi ugh iPhone readymade pickled pop-up. Freegan bicycle rights man braid polaroid, poutine stumptown skateboard.<br> <img src="http://pop.h-cdn.co/assets/15/45/980x490/landscape-1446489786-gettyimages-128111897.jpg" alt="" width="1000" height="500">', 'someurl'),
(4, 'Potato Tornado', 'Sometimes, there are tornados. And sometimes, they spend too much time in potato farms. But it is not necessarily a bad thing because everybody gets free french fries for weeks after. Literally kickstarter chartreuse, gastropub flexitarian bushwick pinterest. Iceland tofu poutine, man braid intelligentsia truffaut pitchfork fanny pack direct trade jean shorts neutra copper mug mlkshk mustache. Four loko food truck pork belly pickled meditation narwhal, squid quinoa whatever tumblr. Authentic gastropub pitchfork, microdosing taxidermy man bun roof party trust fund. Try-hard kinfolk plaid austin humblebrag, art party franzen snackwave tofu organic cold-pressed. Fashion axe meh venmo twee crucifix typewriter mumblecore chartreuse marfa williamsburg artisan, quinoa vinyl. Man braid single-origin coffee mlkshk fanny pack hot chicken, pickled hoodie 90''s craft beer yuccie heirloom. Franzen freegan tbh normcore, kinfolk meh neutra kickstarter. Tbh flexitarian listicle, asymmetrical affogato drinking vinegar squid. DIY intelligentsia irony salvia fap poke live-edge VHS, 8-bit kitsch tacos quinoa master cleanse ethical.<br> <img src="https://i.ytimg.com/vi/0AWRWqZM300/maxresdefault.jpg" alt="" width="1080" height="608">', 'someurl');


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