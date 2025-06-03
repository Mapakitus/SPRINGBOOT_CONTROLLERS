-- Insertar categorías
INSERT INTO categorias (nombre, descripcion) VALUES ('Frutas', 'Productos frescos');
INSERT INTO categorias (nombre, descripcion) VALUES ('Lácteos', 'Productos derivados de la leche');
INSERT INTO categorias (nombre, descripcion) VALUES ('Cereales', 'Productos a base de granos');
INSERT INTO categorias (nombre, descripcion) VALUES ('Carne', 'Productos cárnicos');
INSERT INTO categorias (nombre, descripcion) VALUES ('Bebidas', 'Productos líquidos');
INSERT INTO categorias (nombre, descripcion) VALUES ('Pescado', 'Productos del mar');

-- Insertar productos
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Manzana', 1.99, 100, true, 1);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Pera', 1.99, 200, true, 1);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Salmón ahumado', 12, 10, true, 6);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Leche', 1.40, 50, true, 2);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Queso', 14, 75, true, 2);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Solomillo de ternera', 19, 10, false, 4);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Red bull', 1.99, 300, true, 5);
INSERT INTO productos (nombre, precio, stock, disponible, categoria_id) VALUES ('Bacalao', 10, 30, true, 6);

-- Insertar proveedores
INSERT INTO proveedores (nombre, email, telefono, activo) VALUES ('Proveedor 1', 'proveedor1@mail.com', '111111111', true);
INSERT INTO proveedores (nombre, email, telefono, activo) VALUES ('Proveedor 2', 'proveedor2@mail.com', '222222222', true);
INSERT INTO proveedores (nombre, email, telefono, activo) VALUES ('Proveedor 3', 'proveedor3@mail.com', '333333333', true);
INSERT INTO proveedores (nombre, email, telefono, activo) VALUES ('Proveedor 4', 'proveedor4@mail.com', '444444444', true);
INSERT INTO proveedores (nombre, email, telefono, activo) VALUES ('Proveedor 5', 'proveedor5@mail.com', '555555555', true);
INSERT INTO proveedores (nombre, email, telefono, activo) VALUES ('Proveedor 6', 'proveedor6@mail.com', '666666666', true);

-- Relaciones proveedor-producto (tabla intermedia)
-- Proveedor 1
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (1, 1);
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (1, 3);
-- Proveedor 2
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (2, 5);
-- Proveedor 3
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (3, 6);
-- Proveedor 4
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (4, 1);
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (4, 2);
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (4, 4);
-- Proveedor 5
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (5, 3);
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (5, 6);
-- Proveedor 6
INSERT INTO proveedor_producto (proveedor_id, productos_id) VALUES (6, 2);