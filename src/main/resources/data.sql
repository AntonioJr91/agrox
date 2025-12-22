-- =====================================================
-- DATA.SQL - Dados de teste
-- Compatível com o mapeamento atual
-- =====================================================

-- =========================
-- ROLES
-- =========================
INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

-- =========================
-- ADMIN USER
-- =========================
INSERT INTO users (username, password)
VALUES ('admin',
        '$2y$10$Hw3HrMy.v.ldyMXWac8Ak.7RGSS2LlMcPaaaiIBDLofhvR1e0AHcm');

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
INSERT INTO employee (name,
                      cpf,
                      phone_number,
                      date_of_birth,
                      contract_type,
                      admission_date,
                      dismissal_date)
VALUES ('João Silva', '12345678901', '11999990001', '1985-05-10', 'CLT', '2025-01-01', NULL),
       ('Maria Souza', '12345678902', '11999990002', '1990-08-22', 'SHARECROPPER', '2025-01-01', NULL);

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
-- Defensivo na ordem 2

-- =====================================================
-- FIM DO DATA.SQL
-- =====================================================
