ALTER TABLE images
    ADD COLUMN dish_id BIGINT,
    ADD FOREIGN KEY (dish_id) REFERENCES dishes(id);