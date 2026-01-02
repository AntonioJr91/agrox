-- =====================================================
-- DATA.SQL - DATA TEST
-- =====================================================

-- =========================
-- CATEGORY
-- =========================
INSERT INTO category (name)
VALUES ('Fertilizantes'),
       ('Sementes'),
       ('Defensivos');

-- =========================
-- EMPLOYEE
-- =========================
INSERT INTO employee (
    name,
    cpf,
    phone_number,
    date_of_birth,
    contract_type,
    admission_date,
    dismissal_date
)
VALUES
    ('João Silva',        '12345678901', '11999990001', '1985-05-10', 'CLT',           '2025-01-01', '2025-02-02'),
    ('Maria Souza',       '12345678902', '11999990002', '1990-08-22', 'SHARECROPPER',  '2025-01-01', NULL),
    ('Carlos Pereira',    '12345678903', '11999990003', '1978-03-15', 'CLT',           '2024-11-10', NULL),
    ('Ana Costa',         '12345678904', '11999990004', '1995-12-01', 'CLT',     '2025-02-01', '2025-04-30'),
    ('Bruno Almeida',     '12345678905', '11999990005', '1988-07-19', 'CLT',           '2023-06-15', NULL),
    ('Fernanda Lima',     '12345678906', '11999990006', '1992-09-05', 'SHARECROPPER',  '2024-01-20', NULL),
    ('Rafael Martins',    '12345678907', '11999990007', '1983-11-30', 'CLT',           '2022-03-01', '2024-12-31'),
    ('Juliana Rocha',     '12345678908', '11999990008', '1997-02-14', 'SHARECROPPER',     '2025-03-01', NULL),
    ('Pedro Nogueira',    '12345678909', '11999990009', '1980-06-25', 'CLT',           '2020-08-10', NULL),
    ('Camila Ribeiro',    '12345678910', '11999990010', '1993-10-18', 'SHARECROPPER',  '2024-09-01', NULL);




-- =========================
-- PRODUCT
-- =========================
INSERT INTO product (name,
                     unit_of_measure,
                     category_id,
                     stock_quantity)
VALUES ('Ureia 45%', 'KG', 1, 100),
       ('Semente de Milho', 'UN', 2, 250),
       ('Herbicida X', 'L', 3, 80);

-- =========================
-- SERVICE_ORDER
-- =========================
INSERT INTO service_order (description,
                           employee_id,
                           created_at,
                           finished_at,
                           status)
VALUES ('Aplicação de fertilizante na lavoura A', 1, '2025-02-01', NULL, 'PENDING'),
       ('Pulverização de defensivo na área B', 2, '2025-02-01', '2025-02-02', 'COMPLETED');

-- =========================
-- SERVICE_ORDER_ITEM
-- =========================
INSERT INTO service_order_item (service_order_id,
                                product_id,
                                quantity)
VALUES (1, 1, 20), -- Ureia na ordem 1
       (1, 2, 10), -- Semente na ordem 1
       (2, 3, 5);

-- =====================================================
-- END DATA.SQL
-- =====================================================
