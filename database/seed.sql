-- seed.sql

-- Inserting products
INSERT INTO public.Products (productID, productName, brand, price, quantity, listingDate, image_url, rating, category) VALUES
(1, 'Iphone 15', 'Apple', 800.00, 50, '2024-11-01', '/images/iphone15.jpg', 4.5, 'Smartphones'),
(2, 'Airpods Pro', 'Apple', 125.00, 200, '2024-11-02', '/images/AirpodsPro.jpg', 4.8, 'Headphones'),
(3, 'Surface Pro', 'Microsoft', 1299.99, 30, '2024-10-20', '/images/SurfacePro.jpg', 3.6, 'Computers'),
(4, 'Gaming Console', 'Nintendo', 499.99, 100, '2024-10-01', '/images/nintendo.jpg', 4.9, 'Gaming'),
(5, 'Smartwatch', 'Apple', 259.99, 150, '2024-11-25', '/images/SmartWatch.jpg', 3.6, 'Wearables'),
(6, '4K TV', 'Sony', 899.99, 20, '2024-10-05', '/images/SonyTV.jpg', 5.0, 'Electronics'),
(7, 'Bluetooth Speaker', 'Bose', 59.99, 300, '2024-10-18', '/images/BoseSpeaker.jpg', 4.2, 'Headphones'),
(8, 'Electric Kettle', 'HomeTech', 29.99, 50, '2024-10-22', '/images/Kettle.jpg', 2.3, 'Home Appliances'),
(9, 'Vacuum Cleaner', 'Roborock', 899.94, 40, '2024-10-08', '/images/Roborock.jpg', 3.5, 'Home Appliances'),
(10, 'Fitness Tracker', 'FitGear', 99.99, 120, '2024-10-27', '/images/FitGear.jpg', 3.6, 'Smartwatch'),
(11, 'DSLR Camera', 'Canon', 799.99, 15, '2024-10-03', '/images/Canon.jpg', 4.5, 'Electronics'),
(12, 'Wireless Router', 'NetGear', 129.99, 80, '2024-11-17', '/images/NetGear.jpg', 3.9, 'Electronics'),
(13, 'Gaming Headset', 'Razer', 69.99, 150, '2024-11-28', '/images/Razer.jpg', 4.0, 'Gaming'),
(14, 'External Hard Drive', 'StorageBrand', 89.99, 60, '2024-11-29', '/images/StorageBrand.jpg', 1.2, 'Storage'),
(15, 'Smart Home Hub', 'Google', 149.99, 70, '2024-11-10', '/images/Google.jpg', 3.5, 'Smart Home');


-- Inserting users
INSERT INTO public.Users (userName, addressID, email, phoneNumber, password) VALUES
('alice_smith', 1, 'alice.smith@gmail.com', '123-456-7890', 'password123'),
('bob_johnson', 2, 'bob.johnson@gmail.com', '234-567-8901', 'password456'),
('charlie_brown', 3, 'charlie.brown@gmail.com', '345-678-9012', 'password789'),
('diana_prince', 4, 'diana.prince@gmail.com', '456-789-0123', 'password101'),
('eve_davis', 5, 'eve.davis@gmail.com', '567-890-1234', 'password202'),
('frank_wright', 6, 'frank.wright@gmail.com', '678-901-2345', 'password303'),
('grace_hall', 7, 'grace.hall@gmail.com', '789-012-3456', 'password404'),
('henry_wilson', 8, 'henry.wilson@gmail.com', '890-123-4567', 'password505'),
('ivy_adams', 9, 'ivy.adams@gmail.com', '901-234-5678', 'password606'),
('jack_white', 10, 'jack.white@gmail.com', '012-345-6789', 'password707'),
('karen_black', 11, 'karen.black@gmail.com', '111-222-3333', 'password808'),
('leo_green', 12, 'leo.green@gmail.com', '222-333-4444', 'password909'),
('mia_blue', 13, 'mia.blue@gmail.com', '333-444-5555', 'password010'),
('nina_red', 14, 'nina.red@gmail.com', '444-555-6666', 'password111'),
('oscar_yellow', 15, 'oscar.yellow@gmail.com', '555-666-7777', 'password121');


-- Inserting addresses
INSERT INTO public.Addresses (country, state, city, street, houseNumber, zipcode) VALUES
('USA', 'CA', 'San Francisco', 'Market St', '1234', '94103'),
('USA', 'NY', 'New York', 'Broadway', '5678', '10001'),
('USA', 'TX', 'Austin', 'Congress Ave', '9101', '73301'),
('USA', 'WA', 'Seattle', 'Pine St', '1122', '98101'),
('USA', 'IL', 'Chicago', 'Michigan Ave', '3344', '60601'),
('USA', 'CA', 'Los Angeles', 'Sunset Blvd', '5566', '90001'),
('USA', 'FL', 'Miami', 'Ocean Dr', '7788', '33101'),
('USA', 'NV', 'Las Vegas', 'Fremont St', '9900', '89101'),
('USA', 'MA', 'Boston', 'Boylston St', '1213', '02101'),
('USA', 'CO', 'Denver', '16th St', '1415', '80201'),
('Canada', 'ON', 'Toronto', 'Yonge St', '1617', 'M5E1E3'),
('Canada', 'BC', 'Vancouver', 'Granville St', '1819', 'V6C1A5'),
('Canada', 'QC', 'Montreal', 'Saint-Catherine', '2021', 'H2X1Z7'),
('Canada', 'AB', 'Calgary', 'MacLeod Tr', '2223', 'T2G5E1'),
('Canada', 'NS', 'Halifax', 'Barrington St', '2425', 'B3J3K1');

-- Inserting orders
INSERT INTO public.Orders (userID, numProductsOrdered, dateOrdered, shippingAddressID, totalCost) VALUES
(1, 3, '2024-11-03', 1, 2400), -- After 'Iphone 15' listed on '2024-11-01'
(2, 2, '2024-11-04', 2, 250), -- After 'Airpods Pro' listed on '2024-11-02'
(3, 1, '2024-10-21', 3, 499.99), -- After 'Surface Pro' listed on '2024-10-20'
(4, 4, '2024-10-02', 4, 899.99), -- After 'Gaming Console' listed on '2024-10-01'
(5, 1, '2024-11-26', 5, 2000), -- After 'Smartwatch' listed on '2024-11-25'
(6, 2, '2024-10-06', 6, 1800), -- After '4K TV' listed on '2024-10-05'
(7, 1, '2024-10-19', 7, 60), -- After 'Bluetooth Speaker' listed on '2024-10-18'
(8, 1, '2024-10-23', 8, 30), -- After 'Electric Kettle' listed on '2024-10-22'
(9, 3, '2024-10-09', 9, 2660), -- After 'Vacuum Cleaner' listed on '2024-10-08'
(10, 1, '2024-10-28', 10, 100), -- After 'Fitness Tracker' listed on '2024-10-27'
(11, 2, '2024-10-04', 11, 1600), -- After 'DSLR Camera' listed on '2024-10-03'
(12, 1, '2024-11-18', 12, 130), -- After 'Wireless Router' listed on '2024-11-17'
(13, 1, '2024-11-29', 13, 70), -- After 'Gaming Headset' listed on '2024-11-28'
(14, 1, '2024-11-30', 14, 90), -- After 'External Hard Drive' listed on '2024-11-29'
(15, 1, '2024-11-11', 15, 150); -- After 'Smart Home Hub' listed on '2024-11-10'

-- Inserting OrdersDetails
INSERT INTO public.OrdersDetails (orderID, productID, quantityOrdered) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 1),
(4, 4, 2),
(5, 5, 1),
(6, 6, 3),
(7, 7, 2),
(8, 8, 1),
(9, 9, 1),
(10, 10, 2),
(11, 11, 3),
(12, 12, 2),
(13, 13, 1),
(14, 14, 1),
(15, 15, 2);

-- Inserting Reviews
INSERT INTO public.Reviews (userID, productID, rating, reviewComment, datePosted) VALUES
(1, 1, 5, 'Amazing phone! Totally worth the price.', '2024-11-05'), -- iPhone 15
(2, 2, 4, 'Great sound quality but a bit pricey.', '2024-11-06'), -- Airpods Pro
(3, 3, 5, 'Perfect for work and entertainment.', '2024-10-25'), -- Surface Pro
(4, 4, 4, 'Fun and engaging gaming console.', '2024-10-10'), -- Gaming Console
(5, 5, 5, 'Stylish and functional smartwatch.', '2024-11-30'), -- Smartwatch
(6, 6, 4, 'Excellent TV with sharp picture quality.', '2024-10-08'), -- 4K TV
(7, 7, 5, 'Great bass and volume for its size.', '2024-10-20'), -- Bluetooth Speaker
(8, 8, 4, 'Heats water quickly and looks sleek.', '2024-10-24'), -- Electric Kettle
(9, 9, 5, 'Best vacuum cleaner I have ever used.', '2024-10-15'), -- Vacuum Cleaner
(10, 10, 5, 'Great tracker for workouts and steps.', '2024-10-30'), -- Fitness Tracker
(11, 11, 5, 'Fantastic camera for beginners and pros.', '2024-10-06'), -- DSLR Camera
(12, 12, 4, 'Reliable router with excellent range.', '2024-11-19'), -- Wireless Router
(13, 13, 4, 'Good headset but could be more durable.', '2024-11-30'), -- Gaming Headset
(14, 14, 5, 'Plenty of storage space, highly portable.', '2024-12-01'), -- External Hard Drive
(15, 15, 5, 'Perfect hub for my smart home setup.', '2024-11-12'); -- Smart Home Hub

-- Inserting UserAddress
INSERT INTO public.UserAddresses (userID, addrID) VALUES
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
