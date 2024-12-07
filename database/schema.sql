SET search_path = public;

-- PostgreSQL database dump
--
DROP TABLE IF EXISTS public."Addresses" CASCADE;
DROP TABLE IF EXISTS public."Orders" CASCADE;
DROP TABLE IF EXISTS public."OrdersDetails" CASCADE;
DROP TABLE IF EXISTS public."Products" CASCADE;
DROP TABLE IF EXISTS public."Reviews" CASCADE;
DROP TABLE IF EXISTS public."UserAddresses" CASCADE;
DROP TABLE IF EXISTS public."Users" CASCADE;

-- Dumped from database version 14.15 (Homebrew)
-- Dumped by pg_dump version 14.15 (Homebrew)

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

CREATE TABLE IF NOT EXISTS public."Addresses" (
    "addrID" integer NOT NULL,
    country character varying(100),
    state character varying(100),
    city character varying(100),
    street character varying(100),
    "houseNumber" character varying(100),
    zipcode character varying(100)
);


ALTER TABLE public."Addresses" OWNER TO minhtruongnguyen;

--
-- Name: Orders; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public."Orders" (
    "orderID" integer NOT NULL,
    "userID" integer,
    "numProductsOrdered" integer,
    "dateOrdered" date,
    "shippingAddressID" integer
);


ALTER TABLE public."Orders" OWNER TO minhtruongnguyen;

--
-- Name: OrdersDetails; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public."OrdersDetails" (
    "orderDetailsID" integer NOT NULL,
    "orderID" integer,
    "productID" integer,
    "quantityOrdered" integer
);


ALTER TABLE public."OrdersDetails" OWNER TO minhtruongnguyen;

--
-- Name: Products; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public."Products" (
    "productID" integer NOT NULL,
    "productName" character varying(100),
    brand character varying(50),
    price numeric(10,2),
    quantity integer,
    image_url VARCHAR(255), 
    "listingDate" date
);


ALTER TABLE public."Products" OWNER TO minhtruongnguyen;

--
-- Name: Reviews; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public."Reviews" (
    "reviewID" integer NOT NULL,
    "userID" integer,
    "productID" integer,
    rating integer,
    "reviewComment" text,
    "datePosted" date
);


ALTER TABLE public."Reviews" OWNER TO minhtruongnguyen;

--
-- Name: UserAddresses; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public."UserAddresses" (
    "userID" integer NOT NULL,
    "addrID" integer NOT NULL
);


ALTER TABLE public."UserAddresses" OWNER TO minhtruongnguyen;

--
-- Name: Users; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE IF NOT EXISTS public."Users" (
    "userID" integer NOT NULL,
    "userName" character varying(100),
    "addressID" integer,
    email character varying(100),
    "password" character varying(100),
    "phoneNumber" character varying(100)
);


ALTER TABLE public."Users" OWNER TO minhtruongnguyen;

--
-- Name: Addresses Addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Addresses"
    ADD CONSTRAINT "Addresses_pkey" PRIMARY KEY ("addrID");


--
-- Name: OrdersDetails OrdersDetails_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."OrdersDetails"
    ADD CONSTRAINT "OrdersDetails_pkey" PRIMARY KEY ("orderDetailsID");


--
-- Name: Orders Orders_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Orders"
    ADD CONSTRAINT "Orders_pkey" PRIMARY KEY ("orderID");


--
-- Name: Products Products_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Products"
    ADD CONSTRAINT "Products_pkey" PRIMARY KEY ("productID");


--
-- Name: Reviews Reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Reviews"
    ADD CONSTRAINT "Reviews_pkey" PRIMARY KEY ("reviewID");


--
-- Name: UserAddresses UserAddresses_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."UserAddresses"
    ADD CONSTRAINT "UserAddresses_pkey" PRIMARY KEY ("userID", "addrID");


--
-- Name: Users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY ("userID");


--
-- Name: OrdersDetails OrdersDetails_orderID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."OrdersDetails"
    ADD CONSTRAINT "OrdersDetails_orderID_fkey" FOREIGN KEY ("orderID") REFERENCES public."Orders"("orderID");


--
-- Name: OrdersDetails OrdersDetails_productID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."OrdersDetails"
    ADD CONSTRAINT "OrdersDetails_productID_fkey" FOREIGN KEY ("productID") REFERENCES public."Products"("productID");


--
-- Name: Orders Orders_shippingAddressID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Orders"
    ADD CONSTRAINT "Orders_shippingAddressID_fkey" FOREIGN KEY ("shippingAddressID") REFERENCES public."Addresses"("addrID");


--
-- Name: Orders Orders_userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Orders"
    ADD CONSTRAINT "Orders_userID_fkey" FOREIGN KEY ("userID") REFERENCES public."Users"("userID");


--
-- Name: Reviews Reviews_productID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Reviews"
    ADD CONSTRAINT "Reviews_productID_fkey" FOREIGN KEY ("productID") REFERENCES public."Products"("productID");


--
-- Name: Reviews Reviews_userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."Reviews"
    ADD CONSTRAINT "Reviews_userID_fkey" FOREIGN KEY ("userID") REFERENCES public."Users"("userID");


--
-- Name: UserAddresses UserAddresses_addressID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."UserAddresses"
    ADD CONSTRAINT "UserAddresses_addressID_fkey" FOREIGN KEY ("addrID") REFERENCES public."Addresses"("addrID");


--
-- Name: UserAddresses UserAddresses_userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public."UserAddresses"
    ADD CONSTRAINT "UserAddresses_userID_fkey" FOREIGN KEY ("userID") REFERENCES public."Users"("userID");


--
-- PostgreSQL database dump complete
--

