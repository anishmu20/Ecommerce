-- Insert categories with fixed IDs
INSERT INTO category (id, name, description)
VALUES
  (1, 'Electronics', 'Devices and gadgets'),
  (2, 'Books', 'Printed and digital books'),
  (3, 'Clothing', 'Apparel and accessories'),
  (4, 'Home & Kitchen', 'Home appliances and kitchenware');

-- Insert products referencing correct category IDs
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES
  (1, 'Smartphone', 'Latest model smartphone', 150, 699.99, 1),
  (2, 'Laptop', 'Lightweight laptop with 16GB RAM', 80, 1199.50, 1),
  (3, 'Fiction Novel', 'Bestselling fiction book', 200, 14.99, 2),
  (4, 'Cookbook', 'Recipe book for home cooking', 120, 29.95, 2),
  (5, 'T-Shirt', 'Cotton round neck t-shirt', 300, 19.90, 3),
  (6, 'Jeans', 'Slim fit denim jeans', 180, 49.99, 3),
  (7, 'Blender', 'High-speed kitchen blender', 75, 89.00, 4),
  (8, 'Microwave Oven', 'Compact microwave oven', 50, 149.99, 4);
