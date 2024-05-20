ALTER TABLE dishes
    DROP COLUMN image;

ALTER TABLE dishes
    ADD COLUMN image_id BIGINT;

ALTER TABLE dishes
    ADD FOREIGN KEY (image_id) REFERENCES images(id);
