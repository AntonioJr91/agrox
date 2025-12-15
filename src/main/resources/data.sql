-- =====================================================
-- DATA.SQL - Dados de teste
-- Compatível com o mapeamento atual
-- =====================================================

-- =========================
-- CATEGORY
-- =========================
INSERT INTO category (id, name) VALUES
                                    (1, 'Fertilizantes'),
                                    (2, 'Sementes'),
                                    (3, 'Defensivos');

-- =========================
-- EMPLOYEE
-- =========================
INSERT INTO employee (
    id,
    name,
    cpf,
    phone_number,
    date_of_birth,
    contract_type,
    admission_date,
    dismissal_date
) VALUES
      (1, 'João Silva', '12345678901', '11999990001', '1985-05-10', 'CLT', '2025-01-01', NULL),
      (2, 'Maria Souza', '12345678902', '11999990002', '1990-08-22', 'SHARECROPPER', '2025-01-01', NULL);

-- =========================
-- PRODUCT
-- =========================
INSERT INTO product (
    id,
    name,
    unit_of_measure,
    category_id,
    stock_quantity
) VALUES
      (1, 'Ureia 45%', 'KG', 1, 100),
      (2, 'Semente de Milho', 'UN', 2, 250),
      (3, 'Herbicida X', 'L', 3, 80);

-- =========================
-- SERVICE_ORDER
-- =========================
INSERT INTO service_order (
    id,
    description,
    employee_id,
    created_at,
    finished_at,
    status
) VALUES
      (1, 'Aplicação de fertilizante na lavoura A', 1, '2025-02-01', NULL, 'PENDING'),
      (2, 'Pulverização de defensivo na área B',    2, '2025-02-01', '2025-02-02', 'COMPLETED');

-- =========================
-- SERVICE_ORDER_ITEM
-- =========================
INSERT INTO service_order_item (
    id,
    service_order_id,
    product_id,
    quantity
) VALUES
      (1, 1, 1, 20),  -- Ureia na ordem 1
      (2, 1, 2, 10),  -- Semente na ordem 1
      (3, 2, 3, 5);   -- Defensivo na ordem 2

-- =====================================================
-- FIM DO DATA.SQL
-- =====================================================
