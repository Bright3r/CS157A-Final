
-- PostgreSQL database dump
--
DROP TABLE IF EXISTS public.addresses CASCADE;
DROP TABLE IF EXISTS public.orders CASCADE;
DROP TABLE IF EXISTS public.ordersDetails CASCADE;
DROP TABLE IF EXISTS public.products CASCADE;
DROP TABLE IF EXISTS public.reviews CASCADE;
DROP TABLE IF EXISTS public.userAddresses CASCADE;
DROP TABLE IF EXISTS public.users CASCADE;

-- Dumped from database version 14.15 (Homebrew)
-- Dumped by pg_dump version 14.15 (Homebrew)
SET search_path = public;


GRANT USAGE ON SCHEMA public TO admin;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO admin;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Addresses; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.addresses (
    addrID integer NOT NULL,
    country character varying(100),
    state character varying(100),
    city character varying(100),
    street character varying(100),
    houseNumber character varying(100),
    zipcode character varying(100)
);



--
-- Name: Orders; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.orders (
    orderID integer NOT NULL,
    userID integer,
    numProductsOrdered integer,
    dateOrdered date,
    shippingAddressID integer
);



--
-- Name: OrdersDetails; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.ordersDetails (
    orderDetailsID integer NOT NULL,
    orderID integer,
    productID integer,
    quantityOrdered integer
);



--
-- Name: Products; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.products (
    productID integer NOT NULL,
    productName character varying(100) NOT NULL,
    brand character varying(50),
    price numeric(10,2) NOT NULL,
    quantity integer,
    image_url VARCHAR(255), 
    listingDate date,
    rating FLOAT
);



--
-- Name: Reviews; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.reviews(
    reviewID integer NOT NULL,
    userID integer,
    productID integer,
    rating integer,
    reviewComment text,
    datePosted date
);



--
-- Name: UserAddresses; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.userAddresses (
    userID SERIAL NOT NULL,
    addrID integer NOT NULL
);



--
-- Name: Users; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public.users (
    userID SERIAL,
    userName character varying(100),
    addressID integer,
    email character varying(100),
    password character varying(100),
    phoneNumber character varying(100)
);



--
-- Name: Addresses Addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (addrID);


--
-- Name: OrdersDetails OrdersDetails_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.ordersDetails
    ADD CONSTRAINT ordersDetails_pkey PRIMARY KEY (orderDetailsID);


--
-- Name: Orders Orders_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (orderID);


--
-- Name: Products Products_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (productID);


--
-- Name: Reviews Reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (reviewID);


--
-- Name: UserAddresses UserAddresses_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.userAddresses
    ADD CONSTRAINT userAddresses_pkey PRIMARY KEY (userID, addrID);


--
-- Name: Users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userID);


--
-- Name: OrdersDetails OrdersDetails_orderID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.ordersDetails
    ADD CONSTRAINT ordersDetails_orderID_fkey FOREIGN KEY (orderID) REFERENCES public.orders(orderID);


--
-- Name: OrdersDetails OrdersDetails_productID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.ordersDetails
    ADD CONSTRAINT ordersDetails_productID_fkey FOREIGN KEY (productID) REFERENCES public.products(productID);


--
-- Name: Orders Orders_shippingAddressID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.orders
    ADD CONSTRAINT orders_shippingAddressID_fkey FOREIGN KEY (shippingAddressID) REFERENCES public.addresses(addrID);


--
-- Name: Orders Orders_userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.orders
    ADD CONSTRAINT orders_userID_fkey FOREIGN KEY (userID) REFERENCES public.users(userID);


--
-- Name: Reviews Reviews_productID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.reviews
    ADD CONSTRAINT reviews_productID_fkey FOREIGN KEY (productID) REFERENCES public.products(productID);


--
-- Name: Reviews Reviews_userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.reviews
    ADD CONSTRAINT reviews_userID_fkey FOREIGN KEY (userID) REFERENCES public.Users(userID);


--
-- Name: UserAddresses UserAddresses_addressID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.userAddresses
    ADD CONSTRAINT userAddresses_addressID_fkey FOREIGN KEY (addrID) REFERENCES public.addresses(addrID);


--
-- Name: UserAddresses UserAddresses_userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE public.userAddresses
    ADD CONSTRAINT userAddresses_userID_fkey FOREIGN KEY (userID) REFERENCES public.users(userID);


--
-- PostgreSQL database dump complete
--
