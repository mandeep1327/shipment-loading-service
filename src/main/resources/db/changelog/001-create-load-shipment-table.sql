CREATE TABLE IF NOT EXISTS load_assignment (
load_id SERIAL,
shipment_id integer,
transport_id integer,
PRIMARY KEY(load_id));