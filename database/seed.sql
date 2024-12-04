-- seed.sql

-- Inserting products
INSERT INTO "Products" ("productID", "productName", "brand", "price", "quantity", "listingDate") VALUES
(1, 'Iphone 15', 'Apple', 800.00, 50, '2024-11-01'),
(2, 'Airpods Pro', 'Apple', 125.00, 200, '2024-11-02'),
(3, 'Surface Pro', 'Microsoft', 1299.99, 30, '2024-10-20'),
(4, 'Gaming Console', 'Nintendo', 499.99, 100, '2024-10-01'),
(5, 'Smartwatch', 'Apple', 259.99, 150, '2024-11-25'),
(6, '4K TV', 'Sony', 899.99, 20, '2024-10-05'),
(7, 'Bluetooth Speaker', 'Bose', 59.99, 300, '2024-10-18'),
(8, 'Electric Kettle', 'HomeTech', 29.99, 50, '2024-10-22'),
(9, 'Vacuum Cleaner', 'Roborock', 899.94, 40, '2024-10-08'),
(10, 'Fitness Tracker', 'FitGear', 99.99, 120, '2024-10-27'),
(11, 'DSLR Camera', 'Canon', 799.99, 15, '2024-10-03'),
(12, 'Wireless Router', 'NetGear', 129.99, 80, '2024-11-17'),
(13, 'Gaming Headset', 'Razer', 69.99, 150, '2024-11-28'),
(14, 'External Hard Drive', 'StorageBrand', 89.99, 60, '2024-11-29'),
(15, 'Smart Home Hub', 'Google', 149.99, 70, '2024-11-10');

-- Inserting users
INSERT INTO "Users" ("userID", "userName", "addressID", "email", "phoneNumber") VALUES
(1, 'Alice Smith', 1, 'alice.smith@gmail.com', '123-456-7890'),
(2, 'Bob Johnson', 2, 'bob.johnson@gmail.com', '234-567-8901'),
(3, 'Charlie Brown', 3, 'charlie.brown@gmail.com', '345-678-9012'),
(4, 'Diana Prince', 4, 'diana.prince@gmail.com', '456-789-0123'),
(5, 'Eve Davis', 5, 'eve.davis@gmail.com', '567-890-1234'),
(6, 'Frank Wright', 6, 'frank.wright@gmail.com', '678-901-2345'),
(7, 'Grace Hall', 7, 'grace.hall@gmail.com', '789-012-3456'),
(8, 'Henry Wilson', 8, 'henry.wilson@gmail.com', '890-123-4567'),
(9, 'Ivy Adams', 9, 'ivy.adams@gmail.com', '901-234-5678'),
(10, 'Jack White', 10, 'jack.white@gmail.com', '012-345-6789'),
(11, 'Karen Black', 11, 'karen.black@gmail.com', '111-222-3333'),
(12, 'Leo Green', 12, 'leo.green@gmail.com', '222-333-4444'),
(13, 'Mia Blue', 13, 'mia.blue@gmail.com', '333-444-5555'),
(14, 'Nina Red', 14, 'nina.red@gmail.com', '444-555-6666'),
(15, 'Oscar Yellow', 15, 'oscar.yellow@gmail.com', '555-666-7777');

-- Inserting addresses
INSERT INTO "Addresses" ("addrID", "country", "state", "city", "street", "houseNumber", "zipcode") VALUES
(1, 'USA', 'CA', 'San Francisco', 'Market St', '1234', '94103'),
(2, 'USA', 'NY', 'New York', 'Broadway', '5678', '10001'),
(3, 'USA', 'TX', 'Austin', 'Congress Ave', '9101', '73301'),
(4, 'USA', 'WA', 'Seattle', 'Pine St', '1122', '98101'),
(5, 'USA', 'IL', 'Chicago', 'Michigan Ave', '3344', '60601'),
(6, 'USA', 'CA', 'Los Angeles', 'Sunset Blvd', '5566', '90001'),
(7, 'USA', 'FL', 'Miami', 'Ocean Dr', '7788', '33101'),
(8, 'USA', 'NV', 'Las Vegas', 'Fremont St', '9900', '89101'),
(9, 'USA', 'MA', 'Boston', 'Boylston St', '1213', '02101'),
(10, 'USA', 'CO', 'Denver', '16th St', '1415', '80201'),
(11, 'Canada', 'ON', 'Toronto', 'Yonge St', '1617', 'M5E1E3'),
(12, 'Canada', 'BC', 'Vancouver', 'Granville St', '1819', 'V6C1A5'),
(13, 'Canada', 'QC', 'Montreal', 'Saint-Catherine', '2021', 'H2X1Z7'),
(14, 'Canada', 'AB', 'Calgary', 'MacLeod Tr', '2223', 'T2G5E1'),
(15, 'Canada', 'NS', 'Halifax', 'Barrington St', '2425', 'B3J3K1');

-- Inserting orders
INSERT INTO "Orders" ("orderID", "userID", "numProductsOrdered", "dateOrdered", "shippingAddressID") VALUES
(1, 1, 3, '2024-11-03', 1), -- After 'Iphone 15' listed on '2024-11-01'
(2, 2, 2, '2024-11-04', 2), -- After 'Airpods Pro' listed on '2024-11-02'
(3, 3, 1, '2024-10-21', 3), -- After 'Surface Pro' listed on '2024-10-20'
(4, 4, 4, '2024-10-02', 4), -- After 'Gaming Console' listed on '2024-10-01'
(5, 5, 1, '2024-11-26', 5), -- After 'Smartwatch' listed on '2024-11-25'
(6, 6, 2, '2024-10-06', 6), -- After '4K TV' listed on '2024-10-05'
(7, 7, 1, '2024-10-19', 7), -- After 'Bluetooth Speaker' listed on '2024-10-18'
(8, 8, 1, '2024-10-23', 8), -- After 'Electric Kettle' listed on '2024-10-22'
(9, 9, 3, '2024-10-09', 9), -- After 'Vacuum Cleaner' listed on '2024-10-08'
(10, 10, 1, '2024-10-28', 10), -- After 'Fitness Tracker' listed on '2024-10-27'
(11, 11, 2, '2024-10-04', 11), -- After 'DSLR Camera' listed on '2024-10-03'
(12, 12, 1, '2024-11-18', 12), -- After 'Wireless Router' listed on '2024-11-17'
(13, 13, 1, '2024-11-29', 13), -- After 'Gaming Headset' listed on '2024-11-28'
(14, 14, 1, '2024-11-30', 14), -- After 'External Hard Drive' listed on '2024-11-29'
(15, 15, 1, '2024-11-11', 15); -- After 'Smart Home Hub' listed on '2024-11-10'

-- Inserting OrdersDetails
INSERT INTO "OrdersDetails" ("orderDetailsID", "orderID", "productID", "quantityOrdered") VALUES
(1, 1, 1, 2),
(2, 2, 2, 1),
(3, 3, 3, 1),
(4, 4, 4, 2),
(5, 5, 5, 1),
(6, 6, 6, 3),
(7, 7, 7, 2),
(8, 8, 8, 1),
(9, 9, 9, 1),
(10, 10, 10, 2),
(11, 11, 11, 3),
(12, 12, 12, 2),
(13, 13, 13, 1),
(14, 14, 14, 1),
(15, 15, 15, 2);

-- Inserting Reviews
INSERT INTO "Reviews" ("reviewID", "userID", "productID", "rating", "reviewComment", "datePosted") VALUES
(1, 1, 1, 5, 'Amazing phone! Totally worth the price.', '2024-11-05'), -- iPhone 15
(2, 2, 2, 4, 'Great sound quality but a bit pricey.', '2024-11-06'), -- Airpods Pro
(3, 3, 3, 5, 'Perfect for work and entertainment.', '2024-10-25'), -- Surface Pro
(4, 4, 4, 4, 'Fun and engaging gaming console.', '2024-10-10'), -- Gaming Console
(5, 5, 5, 5, 'Stylish and functional smartwatch.', '2024-11-30'), -- Smartwatch
(6, 6, 6, 4, 'Excellent TV with sharp picture quality.', '2024-10-08'), -- 4K TV
(7, 7, 7, 5, 'Great bass and volume for its size.', '2024-10-20'), -- Bluetooth Speaker
(8, 8, 8, 4, 'Heats water quickly and looks sleek.', '2024-10-24'), -- Electric Kettle
(9, 9, 9, 5, 'Best vacuum cleaner I have ever used.', '2024-10-15'), -- Vacuum Cleaner
(10, 10, 10, 5, 'Great tracker for workouts and steps.', '2024-10-30'), -- Fitness Tracker
(11, 11, 11, 5, 'Fantastic camera for beginners and pros.', '2024-10-06'), -- DSLR Camera
(12, 12, 12, 4, 'Reliable router with excellent range.', '2024-11-19'), -- Wireless Router
(13, 13, 13, 4, 'Good headset but could be more durable.', '2024-11-30'), -- Gaming Headset
(14, 14, 14, 5, 'Plenty of storage space, highly portable.', '2024-12-01'), -- External Hard Drive
(15, 15, 15, 5, 'Perfect hub for my smart home setup.', '2024-11-12'); -- Smart Home Hub

-- Inserting UserAddress
INSERT INTO "UserAddresses" ("userID", "addrID") VALUES
(1, 1),  -- User 1 linked to Address 1
(2, 2),  -- User 2 linked to Address 2
(3, 3),  -- User 3 linked to Address 3
(4, 4),  -- User 4 linked to Address 4
(5, 5),  -- User 5 linked to Address 5
(6, 6),  -- User 6 linked to Address 6
(7, 7),  -- User 7 linked to Address 7
(8, 8),  -- User 8 linked to Address 8
(9, 9),  -- User 9 linked to Address 9
(10, 10), -- User 10 linked to Address 10
(11, 11), -- User 11 linked to Address 11
(12, 12), -- User 12 linked to Address 12
(13, 13), -- User 13 linked to Address 13
(14, 14), -- User 14 linked to Address 14
(15, 15); -- User 15 linked to Address 15
