use backend;
create table user (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username varchar(45) default null,
    fl_name varchar(255) default null,
    password_user varchar(45) default null,
    id_role INTEGER,
    id_status INTEGER,
    foreign key (id_role) references role_user(id),
    foreign key (id_status) references status_user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

ALTER TABLE user
ADD FOREIGN KEY (id_role) references role_user(id);

create table role_user (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  role_user varchar(255) default null,
  unique key `role_UNIQUE` (role_user)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table status_user (
   id INTEGER AUTO_INCREMENT PRIMARY KEY,
   status_user varchar(255) default null,
   unique key `status_UNIQUE` (status_user)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table like_post (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_user INTEGER,
    id_post INTEGER,
    foreign key (id_post) references post(id),
    foreign key (id_user) references user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table comment_post (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_user INTEGER,
    tex varchar(255),
    data_post date,
    id_post INTEGER,
    foreign key (id_post) references post(id),
    foreign key (id_user) references user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table complaint (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_user INTEGER,
    date_complaimnt date,
    complaint varchar(255),
    id_post INTEGER,
    id_status_complaint INTEGER,
    foreign key (id_post) references post(id),
    foreign key (id_user) references user(id),
    foreign key (id_status_complaint) references status_complaint(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table status_complaint (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    status_complaint varchar(255) default null,
	unique key `role_UNIQUE` (status_complaint)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table post (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    id_user INTEGER,
    path_photo varchar(255),
    date_post date,
    text_post varchar(255),
    foreign key (id_user) references user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table hashtag (
    name varchar(45) primary key,
    unique key `name_UNIQUE` (name)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table post_hashtag (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name varchar(45),
  id_post integer,
  foreign key (name) references hashtag(name),
  foreign key (id_post) references post(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

create table subscriptions (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  id_following integer not null,
  id_followers integer not null,
  foreign key (id_following) references user(id),
  foreign key (id_followers) references user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1;
