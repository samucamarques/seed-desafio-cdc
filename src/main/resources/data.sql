INSERT INTO Author (name, mail_address, description, created_at) VALUES ('Samuel', 'samuel@email.com', 'Nice guy', '2020-11-11 10:38:04.191954')

INSERT INTO Category (name) VALUES ('Comedy')

INSERT INTO Book (title, briefing, summary, price, pages, isbn, release_at, category_id, author_id) VALUES ('The Lord of the Bugs', 'The story of a little programmer trying to burn the bugs in his code', '1 - Chapter One</br> 2 - Chapter Two', 25.90, 120, '1-XAS-3', '2020-11-14', 1, 1)
INSERT INTO Book (title, briefing, summary, price, pages, isbn, release_at, category_id, author_id) VALUES ('The Lord of the CDD', 'The story of a little programmer that wants to simplify everything', '1 - Chapter One</br> 2 - Chapter Two', 55.92, 170, '1-XAS-4', '2020-11-14', 1, 1)

INSERT INTO Country (name) VALUES ('Middle Earth')

INSERT INTO State (name, country_id) VALUES ('Shire', 1)

INSERT INTO Coupon (code, discount, expiration_date) VALUES ('cupom-expirado', 50, '2000-12-31')
INSERT INTO Coupon (code, discount, expiration_date) VALUES ('cupom-valido', 50, '2099-12-31')