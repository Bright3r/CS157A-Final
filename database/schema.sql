--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Postgres.app)
-- Dumped by pg_dump version 17.2 (Postgres.app)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: addresses; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.addresses (
    addrid integer NOT NULL,
    country character varying(100),
    state character varying(100),
    city character varying(100),
    street character varying(100),
    housenumber character varying(10),
    zipcode character varying(20)
);


ALTER TABLE public.addresses OWNER TO minhtruongnguyen;

--
-- Name: addresses_addrid_seq; Type: SEQUENCE; Schema: public; Owner: minhtruongnguyen
--

CREATE SEQUENCE public.addresses_addrid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.addresses_addrid_seq OWNER TO minhtruongnguyen;

--
-- Name: addresses_addrid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: minhtruongnguyen
--

ALTER SEQUENCE public.addresses_addrid_seq OWNED BY public.addresses.addrid;


--
-- Name: orderdetails; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.orderdetails (
    orderdetailsid integer NOT NULL,
    orderid integer NOT NULL,
    productid integer NOT NULL,
    quantityordered integer NOT NULL
);


ALTER TABLE public.orderdetails OWNER TO minhtruongnguyen;

--
-- Name: orderdetails_orderdetailsid_seq; Type: SEQUENCE; Schema: public; Owner: minhtruongnguyen
--

CREATE SEQUENCE public.orderdetails_orderdetailsid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orderdetails_orderdetailsid_seq OWNER TO minhtruongnguyen;

--
-- Name: orderdetails_orderdetailsid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: minhtruongnguyen
--

ALTER SEQUENCE public.orderdetails_orderdetailsid_seq OWNED BY public.orderdetails.orderdetailsid;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.orders (
    orderid integer NOT NULL,
    userid integer NOT NULL,
    numproductsordered integer NOT NULL,
    dateordered date NOT NULL,
    shippingaddressid integer NOT NULL
);


ALTER TABLE public.orders OWNER TO minhtruongnguyen;

--
-- Name: orders_orderid_seq; Type: SEQUENCE; Schema: public; Owner: minhtruongnguyen
--

CREATE SEQUENCE public.orders_orderid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_orderid_seq OWNER TO minhtruongnguyen;

--
-- Name: orders_orderid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: minhtruongnguyen
--

ALTER SEQUENCE public.orders_orderid_seq OWNED BY public.orders.orderid;


--
-- Name: products; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.products (
    productid integer NOT NULL,
    productname character varying(100) NOT NULL,
    brand character varying(50),
    price numeric(10,2) NOT NULL,
    quantity integer NOT NULL,
    listingdate date NOT NULL
);


ALTER TABLE public.products OWNER TO minhtruongnguyen;

--
-- Name: products_productid_seq; Type: SEQUENCE; Schema: public; Owner: minhtruongnguyen
--

CREATE SEQUENCE public.products_productid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_productid_seq OWNER TO minhtruongnguyen;

--
-- Name: products_productid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: minhtruongnguyen
--

ALTER SEQUENCE public.products_productid_seq OWNED BY public.products.productid;


--
-- Name: reviews; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.reviews (
    reviewid integer NOT NULL,
    userid integer NOT NULL,
    productid integer NOT NULL,
    rating integer,
    reviewcomment text,
    dateposted date NOT NULL,
    CONSTRAINT reviews_rating_check CHECK (((rating >= 1) AND (rating <= 5)))
);


ALTER TABLE public.reviews OWNER TO minhtruongnguyen;

--
-- Name: reviews_reviewid_seq; Type: SEQUENCE; Schema: public; Owner: minhtruongnguyen
--

CREATE SEQUENCE public.reviews_reviewid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reviews_reviewid_seq OWNER TO minhtruongnguyen;

--
-- Name: reviews_reviewid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: minhtruongnguyen
--

ALTER SEQUENCE public.reviews_reviewid_seq OWNED BY public.reviews.reviewid;


--
-- Name: useraddresses; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.useraddresses (
    userid integer NOT NULL,
    addrid integer NOT NULL
);


ALTER TABLE public.useraddresses OWNER TO minhtruongnguyen;

--
-- Name: users; Type: TABLE; Schema: public; Owner: minhtruongnguyen
--

CREATE TABLE public.users (
    userid integer NOT NULL,
    username character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    phonenumber character varying(15),
    addressid integer
);


ALTER TABLE public.users OWNER TO minhtruongnguyen;

--
-- Name: users_userid_seq; Type: SEQUENCE; Schema: public; Owner: minhtruongnguyen
--

CREATE SEQUENCE public.users_userid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_userid_seq OWNER TO minhtruongnguyen;

--
-- Name: users_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: minhtruongnguyen
--

ALTER SEQUENCE public.users_userid_seq OWNED BY public.users.userid;


--
-- Name: addresses addrid; Type: DEFAULT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.addresses ALTER COLUMN addrid SET DEFAULT nextval('public.addresses_addrid_seq'::regclass);


--
-- Name: orderdetails orderdetailsid; Type: DEFAULT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orderdetails ALTER COLUMN orderdetailsid SET DEFAULT nextval('public.orderdetails_orderdetailsid_seq'::regclass);


--
-- Name: orders orderid; Type: DEFAULT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orders ALTER COLUMN orderid SET DEFAULT nextval('public.orders_orderid_seq'::regclass);


--
-- Name: products productid; Type: DEFAULT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.products ALTER COLUMN productid SET DEFAULT nextval('public.products_productid_seq'::regclass);


--
-- Name: reviews reviewid; Type: DEFAULT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.reviews ALTER COLUMN reviewid SET DEFAULT nextval('public.reviews_reviewid_seq'::regclass);


--
-- Name: users userid; Type: DEFAULT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.users ALTER COLUMN userid SET DEFAULT nextval('public.users_userid_seq'::regclass);


--
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (addrid);


--
-- Name: orderdetails orderdetails_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orderdetails
    ADD CONSTRAINT orderdetails_pkey PRIMARY KEY (orderdetailsid);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (orderid);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (productid);


--
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (reviewid);


--
-- Name: useraddresses useraddresses_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.useraddresses
    ADD CONSTRAINT useraddresses_pkey PRIMARY KEY (userid, addrid);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- Name: orderdetails orderdetails_orderid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orderdetails
    ADD CONSTRAINT orderdetails_orderid_fkey FOREIGN KEY (orderid) REFERENCES public.orders(orderid);


--
-- Name: orderdetails orderdetails_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orderdetails
    ADD CONSTRAINT orderdetails_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(productid);


--
-- Name: orders orders_shippingaddressid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_shippingaddressid_fkey FOREIGN KEY (shippingaddressid) REFERENCES public.addresses(addrid);


--
-- Name: orders orders_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: reviews reviews_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(productid);


--
-- Name: reviews reviews_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: useraddresses useraddresses_addrid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.useraddresses
    ADD CONSTRAINT useraddresses_addrid_fkey FOREIGN KEY (addrid) REFERENCES public.addresses(addrid);


--
-- Name: useraddresses useraddresses_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.useraddresses
    ADD CONSTRAINT useraddresses_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: users users_addressid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: minhtruongnguyen
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_addressid_fkey FOREIGN KEY (addressid) REFERENCES public.addresses(addrid);


--
-- PostgreSQL database dump complete
--

