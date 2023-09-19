CREATE TABLE IF NOT EXISTS transport (
transport_id SERIAL,
capacity DOUBLE PRECISION,
current_load DOUBLE PRECISION,
dimension JSONB,
PRIMARY KEY(transport_id));