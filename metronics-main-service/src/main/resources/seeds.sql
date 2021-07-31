-- PARTS
use metronics_parts;
insert into part (id, description, part_number, purchase_price, quantity, sale_price)
values (1, "12v solenoid", "RA13340", 24.99, 3, 75.00);
insert into part (id, description, part_number, purchase_price, quantity, sale_price)
values (2, "oil drain valve", "RA14567", 13.49, 1, 35.00);
insert into part (id, description, part_number, purchase_price, quantity, sale_price)
values (3, "filter-drier", "RA13172", 39.99, 12, 80.00);
insert into part (id, description, part_number, purchase_price, quantity, sale_price)
values (4, "replacement coupler set", "RA24440", 42.49, 2, 90.00);
insert into part (id, description, part_number, purchase_price, quantity, sale_price)
values (5, "power supply 34788ni-h", "RA45450", 169.99, 1, 325.00);

-- REQUESTS
use metronics_requests;
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (1, 1, "06/23/2021", "", "Same problem as last time.", "Replaced the recycle solenoid again. Last one defective and under warranty.", "completed", "callback");
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (2, 2, "", "", "I think my machine needs maintenance. It keeps asking for a new filter.", "", "scheduled", "maintenance");
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (3, 1, "06/12/2021", "11694", "I'm getting a high pressure every time half-way through recovery cycle.", "Replaced recycle solenoid on a 34788NI and calibrated the scales.", "completed", "repair");
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (4, 3, "", "", "The machine takes too long to charge. I need someone to come look at it. Thanks.", "", "waiting", "unknown");
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (5, 4, "06/12/2021", "11693", "I have two machines that need maintenance", "Changed filter/oil, calibrated scales", "completed", "maintenance");
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (6, 2, "", "", "I have two machines that need maintenance", "", "waiting", "unknown");
insert into requests (id, customer_id, date_completed, invoice_number, problem_notes, repair_notes, status, type)
values (7, 3, "", "", "Unit says it's going to lock out soon. Needs to be serviced", "", "scheduled", "maintenance");

-- CUSTOMERS
use metronics_customers;
insert into customer (id, business_name, contact_name, phone, street, city, state, zipcode)
values (1, "Elk Grove Automotive", "Mike", "916-234-5345", "12 S Peabody Ln", "Elk Grove", "CA", "95757");
insert into customer (id, business_name, contact_name, phone, street, city, state, zipcode)
values (2, "Jackson Auto", "Jeff or Steve", "916-354-6321", "5425 Stockton Blvd", "Sacramento", "CA", "95845");
insert into customer (id, business_name, contact_name, phone, street, city, state, zipcode)
values (3, "Folsom Ford", "Bill Baxton", "209-234-5345", "5 Automall Way", "Folsom", "CA", "95834");
insert into customer (id, business_name, contact_name, phone, street, city, state, zipcode)
values (4, "High Tech Auto", "Hector", "209-756-3456", "12 Freeport Blvd", "Sacramento", "CA", "95856");

-- USERS & AUTHORITIES
use metronics;
insert into users (id, username, password, enabled)
values (1, "bob", "$2a$10$EHvNOltPQuMeggxQdF.GYOBLjyORe44Xlb/hxQXTqNTVko99rZp/y", 1);
insert into authorities (id, username, user_id, authority)
values (1, "bob", 1, "admin");