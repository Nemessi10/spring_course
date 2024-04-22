ALTER TABLE `user_roles`
DROP FOREIGN KEY `user_roles_ibfk_1`,
DROP FOREIGN KEY `user_roles_ibfk_2`;

ALTER TABLE `user_roles`
    ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE;
