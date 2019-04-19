CREATE TABLE IF NOT EXISTS USERS (
user_id INT AUTO_INCREMENT,
full_name VARCHAR(255) NOT NULL,
email_address VARCHAR(255) NOT NULL UNIQUE,
phone_number VARCHAR(100) NOT NULL UNIQUE,
user_password VARCHAR(255) NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS RECIPES(
recipe_id INT AUTO_INCREMENT,
recipe_name VARCHAR(255) NOT NULL UNIQUE,
recipe_cuisine VARCHAR(255) NOT NULL,
recipe_description VARCHAR(255) NOT NULL,
recipe_ingredients VARCHAR(255) NOT NULL,
recipe_steps VARCHAR(255) NOT NULL,
user_id INT,
rejection_reason VARCHAR(255),
recipe_lastupdate_time DATETIME,
status VARCHAR(45),
PRIMARY KEY (recipe_id),
FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE TABLE IF NOT EXISTS RATINGS(
user_id INT,
recipe_id INT,
recipe_rating INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES USERS(user_id),
FOREIGN KEY (recipe_id) REFERENCES RECIPES(recipe_id)
);

CREATE TABLE IF NOT EXISTS CATEGORIES(
category_id INT AUTO_INCREMENT,
category_name VARCHAR(255) NOT NULL UNIQUE,
PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS RECIPE_CATEGORIES(
recipe_id INT,
category_id INT,
FOREIGN KEY (recipe_id) REFERENCES RECIPES(recipe_id),
FOREIGN KEY (category_id) REFERENCES CATEGORIES(category_id)
);

CREATE TABLE IF NOT EXISTS FOLLOWERS(
user_id INT,
follower_id INT,
FOREIGN KEY (user_id) REFERENCES USERS(user_id),
FOREIGN KEY (follower_id) REFERENCES USERS(user_id)
);






CREATE TABLE IF NOT EXISTS SEARCHSUGGESTIONS(
user_id INT(11),
search VARCHAR(255),
FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE TABLE IF NOT EXISTS RECIPE_IMAGES(
recipe_id INT(11),
image_path VARCHAR(255),
image_name VARCHAR(45),
FOREIGN KEY (recipe_id) REFERENCES RECIPES(recipe_id)
);

CREATE TABLE IF NOT EXISTS LOGINFORMATION(
log_time VARCHAR(255),
log_level VARCHAR(255),
log_message VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS PASSWORD_CONFIG(
password_Length INT,
have_Lowercase_Character boolean,
have_Uppercase_Character boolean,
have_Number boolean,
have_Special_Symbol boolean
);




