USE sparkfly_shop;

INSERT INTO categorias (nombre_categoria, estado) VALUES 
('Drinkware', 1),    -- ID 1
('Home Decor', 1),   -- ID 2
('Apparel', 1),      -- ID 3
('Stationery', 1),   -- ID 4
('Jewelry', 1),      -- ID 5
('Bags', 1);         -- ID 6

INSERT INTO productos (nombre, descripcion, precio, stock, imagen_url, id_categoria) VALUES 
('folklore Cabin Ceramic Mug', 'Taza de cerámica inspirada en la cabaña de folklore', 299.00, 15, 'img/folklore-mug.jpg', 1),
('Lover House Corduroy Pillow', 'Cojín de pana temático de la casa Lover', 450.00, 10, 'img/lover-pillow.jpg', 2),
('reputation Crushed Velvet Crewneck', 'Sudadera de terciopelo estilo reputation', 750.00, 12, 'img/reputation-crewneck.jpg', 3),
('The Tortured Poets Department Notebook', 'Libreta de colección TTPD', 220.00, 25, 'img/ttpd-notebook.jpg', 4),
('Blank Space Heart Cake Ring', 'Anillo inspirado en el pastel de Blank Space', 400.00, 0, 'img/blank-space-ring.jpg', 5),
('The Eras Tour Reusable Tote Bag', 'Bolsa ecológica reutilizable The Eras Tour', 349.00, 18, 'img/eras-tote.jpg', 6);
SELECT * FROM productos;
SELECT * FROM productos;
