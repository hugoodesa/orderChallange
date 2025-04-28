-- Insert test order
INSERT INTO orders (
    order_id,
    request_date,
    total_value,
    status,
    processing_start_date,
    processing_end_date,
    last_updated_at
) VALUES (
    'ORDER-001',
    CURRENT_TIMESTAMP,
    150.00,
    'RECEIVED',
    CURRENT_TIMESTAMP,
    NULL,
    CURRENT_TIMESTAMP
);

-- Insert test order items for the order
INSERT INTO order_items (
    order_id,
    product_id,
    quantity,
    unit_price,
    item_total
) VALUES 
    (1, 'PROD-001', 2, 50.00, 100.00),
    (1, 'PROD-002', 1, 50.00, 50.00); 