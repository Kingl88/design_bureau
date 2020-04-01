INSERT INTO user (id, enabled, password, username)
VALUES (1, true ,'$2a$10$OZiH94UkD03Vbn9fK7IyTOUC6Ry6mLHauso5TRw2Kx1lnAd.jptfW', 'admin');
INSERT INTO user_role (user_id, authorities)
VALUES (1 ,'ROLE_ADMIN');