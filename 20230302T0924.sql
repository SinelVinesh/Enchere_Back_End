--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

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
-- Name: admin; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.admin (
    id integer NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


ALTER TABLE public.admin OWNER TO enchere;

--
-- Name: admin_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_id_seq OWNER TO enchere;

--
-- Name: admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.admin_id_seq OWNED BY public.admin.id;


--
-- Name: admin_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_seq OWNER TO enchere;

--
-- Name: admin_token; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.admin_token (
    id integer NOT NULL,
    value character varying(255) NOT NULL,
    expiration_date timestamp(6) without time zone NOT NULL,
    creation_date timestamp(6) without time zone NOT NULL,
    adminid integer NOT NULL
);


ALTER TABLE public.admin_token OWNER TO enchere;

--
-- Name: admin_token_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_token_id_seq OWNER TO enchere;

--
-- Name: admin_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.admin_token_id_seq OWNED BY public.admin_token.id;


--
-- Name: admin_token_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_token_seq OWNER TO enchere;

--
-- Name: app_user; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.app_user (
    id integer NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    account_balance real NOT NULL
);


ALTER TABLE public.app_user OWNER TO enchere;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_id_seq OWNER TO enchere;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;


--
-- Name: app_user_recharge_request; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.app_user_recharge_request (
    id integer NOT NULL,
    date date NOT NULL,
    amount real NOT NULL,
    app_userid integer NOT NULL
);


ALTER TABLE public.app_user_recharge_request OWNER TO enchere;

--
-- Name: app_user_recharge_request_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_request_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_request_id_seq OWNER TO enchere;

--
-- Name: app_user_recharge_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_recharge_request_id_seq OWNED BY public.app_user_recharge_request.id;


--
-- Name: app_user_recharge_request_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_request_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_request_seq OWNER TO enchere;

--
-- Name: app_user_recharge_state_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.app_user_recharge_state_history (
    id integer NOT NULL,
    date date,
    app_user_recharge_requestid integer NOT NULL,
    recharge_stateid integer NOT NULL
);


ALTER TABLE public.app_user_recharge_state_history OWNER TO enchere;

--
-- Name: app_user_recharge_state_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_state_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_state_history_id_seq OWNER TO enchere;

--
-- Name: app_user_recharge_state_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_recharge_state_history_id_seq OWNED BY public.app_user_recharge_state_history.id;


--
-- Name: app_user_recharge_state_history_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_state_history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_state_history_seq OWNER TO enchere;

--
-- Name: app_user_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_seq OWNER TO enchere;

--
-- Name: app_user_token; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.app_user_token (
    id integer NOT NULL,
    value character varying(255) NOT NULL,
    expiration_date timestamp(6) without time zone NOT NULL,
    creation_date timestamp(6) without time zone NOT NULL,
    app_userid integer NOT NULL
);


ALTER TABLE public.app_user_token OWNER TO enchere;

--
-- Name: app_user_token_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_token_id_seq OWNER TO enchere;

--
-- Name: app_user_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_token_id_seq OWNED BY public.app_user_token.id;


--
-- Name: app_user_token_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_token_seq OWNER TO enchere;

--
-- Name: auction; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.auction (
    id integer NOT NULL,
    description text NOT NULL,
    app_userid integer NOT NULL,
    category_id integer NOT NULL,
    start_date timestamp(6) without time zone NOT NULL,
    end_date timestamp(6) without time zone NOT NULL,
    starting_price real,
    bid_step real,
    auction_stateid integer NOT NULL
);


ALTER TABLE public.auction OWNER TO enchere;

--
-- Name: auction_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_id_seq OWNER TO enchere;

--
-- Name: auction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.auction_id_seq OWNED BY public.auction.id;


--
-- Name: auction_mised; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.auction_mised (
    id bigint NOT NULL,
    bid_step real,
    description character varying(255) NOT NULL,
    end_date timestamp(6) without time zone NOT NULL,
    max_mise real,
    mise real,
    nombre_mise integer,
    start_date timestamp(6) without time zone NOT NULL,
    starting_price real NOT NULL,
    app_userid bigint,
    auction_stateid bigint,
    category_id bigint
);


ALTER TABLE public.auction_mised OWNER TO enchere;

--
-- Name: auction_mised_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_mised_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_mised_seq OWNER TO enchere;

--
-- Name: auction_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_seq OWNER TO enchere;

--
-- Name: auction_state; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.auction_state (
    id integer NOT NULL,
    value character varying(25) NOT NULL
);


ALTER TABLE public.auction_state OWNER TO enchere;

--
-- Name: auction_state_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_state_id_seq OWNER TO enchere;

--
-- Name: auction_state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.auction_state_id_seq OWNED BY public.auction_state.id;


--
-- Name: auction_state_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_state_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_state_seq OWNER TO enchere;

--
-- Name: bid_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.bid_history (
    id integer NOT NULL,
    amount real NOT NULL,
    date timestamp(6) without time zone NOT NULL,
    app_userid integer NOT NULL,
    bidid integer NOT NULL,
    app_user_id bigint,
    auction_id bigint
);


ALTER TABLE public.bid_history OWNER TO enchere;

--
-- Name: bid_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.bid_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_history_id_seq OWNER TO enchere;

--
-- Name: bid_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.bid_history_id_seq OWNED BY public.bid_history.id;


--
-- Name: bid_history_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.bid_history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_history_seq OWNER TO enchere;

--
-- Name: bid_photos; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.bid_photos (
    id integer NOT NULL,
    bid_id integer NOT NULL,
    picture bytea NOT NULL
);


ALTER TABLE public.bid_photos OWNER TO enchere;

--
-- Name: bid_photos_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.bid_photos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_photos_id_seq OWNER TO enchere;

--
-- Name: bid_photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.bid_photos_id_seq OWNED BY public.bid_photos.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.category OWNER TO enchere;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO enchere;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: category_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.category_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_seq OWNER TO enchere;

--
-- Name: commision; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.commision (
    id integer NOT NULL,
    amount real NOT NULL,
    parametersid integer NOT NULL,
    auctionid integer NOT NULL
);


ALTER TABLE public.commision OWNER TO enchere;

--
-- Name: commision_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.commision_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.commision_id_seq OWNER TO enchere;

--
-- Name: commision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.commision_id_seq OWNED BY public.commision.id;


--
-- Name: daily_auction; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.daily_auction (
    id bigint NOT NULL,
    amount real,
    date timestamp(6) without time zone
);


ALTER TABLE public.daily_auction OWNER TO enchere;

--
-- Name: daily_auction_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.daily_auction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.daily_auction_id_seq OWNER TO enchere;

--
-- Name: daily_auction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.daily_auction_id_seq OWNED BY public.daily_auction.id;


--
-- Name: daily_auction_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.daily_auction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.daily_auction_seq OWNER TO enchere;

--
-- Name: daily_sales; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.daily_sales (
    id bigint NOT NULL,
    amount real,
    date timestamp(6) without time zone
);


ALTER TABLE public.daily_sales OWNER TO enchere;

--
-- Name: daily_sales_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.daily_sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.daily_sales_id_seq OWNER TO enchere;

--
-- Name: daily_sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.daily_sales_id_seq OWNED BY public.daily_sales.id;


--
-- Name: recharge_state; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.recharge_state (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);


ALTER TABLE public.recharge_state OWNER TO enchere;

--
-- Name: recharge_state_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.recharge_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recharge_state_id_seq OWNER TO enchere;

--
-- Name: recharge_state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.recharge_state_id_seq OWNED BY public.recharge_state.id;


--
-- Name: recharge_state_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.recharge_state_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recharge_state_seq OWNER TO enchere;

--
-- Name: reload_request; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.reload_request (
    id bigint NOT NULL,
    amount real NOT NULL,
    date timestamp with time zone DEFAULT now(),
    app_user_id bigint
);


ALTER TABLE public.reload_request OWNER TO enchere;

--
-- Name: reload_request_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.reload_request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reload_request_id_seq OWNER TO enchere;

--
-- Name: reload_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.reload_request_id_seq OWNED BY public.reload_request.id;


--
-- Name: reload_request_state_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.reload_request_state_history (
    id bigint NOT NULL,
    date timestamp with time zone DEFAULT now(),
    reload_request_id bigint,
    reload_state_id bigint
);


ALTER TABLE public.reload_request_state_history OWNER TO enchere;

--
-- Name: reload_request_state_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.reload_request_state_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reload_request_state_history_id_seq OWNER TO enchere;

--
-- Name: reload_request_state_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.reload_request_state_history_id_seq OWNED BY public.reload_request_state_history.id;


--
-- Name: reload_state; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.reload_state (
    id bigint NOT NULL,
    description character varying(255) NOT NULL
);


ALTER TABLE public.reload_state OWNER TO enchere;

--
-- Name: reload_state_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.reload_state_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reload_state_seq OWNER TO enchere;

--
-- Name: setting; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.setting (
    id integer NOT NULL,
    key character varying(255) NOT NULL,
    creation_date date NOT NULL
);


ALTER TABLE public.setting OWNER TO enchere;

--
-- Name: setting_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.setting_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.setting_id_seq OWNER TO enchere;

--
-- Name: setting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.setting_id_seq OWNED BY public.setting.id;


--
-- Name: setting_value_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.setting_value_history (
    id integer NOT NULL,
    update_date date NOT NULL,
    value character varying(255) NOT NULL,
    setiingid integer NOT NULL
);


ALTER TABLE public.setting_value_history OWNER TO enchere;

--
-- Name: setting_value_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.setting_value_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.setting_value_history_id_seq OWNER TO enchere;

--
-- Name: setting_value_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.setting_value_history_id_seq OWNED BY public.setting_value_history.id;


--
-- Name: settings; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.settings (
    id bigint NOT NULL,
    creation_date timestamp with time zone,
    key character varying(255)
);


ALTER TABLE public.settings OWNER TO enchere;

--
-- Name: settings_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.settings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.settings_id_seq OWNER TO enchere;

--
-- Name: settings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.settings_id_seq OWNED BY public.settings.id;


--
-- Name: settings_value_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.settings_value_history (
    id bigint NOT NULL,
    date timestamp with time zone,
    value character varying(255),
    settings_id bigint
);


ALTER TABLE public.settings_value_history OWNER TO enchere;

--
-- Name: settings_value_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.settings_value_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.settings_value_history_id_seq OWNER TO enchere;

--
-- Name: settings_value_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.settings_value_history_id_seq OWNED BY public.settings_value_history.id;


--
-- Name: v_app_user; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_app_user AS
 SELECT (row_number() OVER (ORDER BY b.app_userid))::integer AS id,
    b.app_userid,
    (au.account_balance - sum(b.amount)) AS money_can_use
   FROM ((public.bid_history b
     JOIN public.app_user au ON ((b.app_userid = au.id)))
     JOIN public.auction a ON ((a.id = b.bidid)))
  WHERE ((a.auction_stateid = 1) AND (b.id IN ( SELECT max(b_1.id) AS max
           FROM public.bid_history b_1
          GROUP BY b_1.bidid)))
  GROUP BY b.app_userid, au.account_balance;


ALTER TABLE public.v_app_user OWNER TO enchere;

--
-- Name: v_app_user_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.v_app_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.v_app_user_seq OWNER TO enchere;

--
-- Name: v_auction_with_mise; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_auction_with_mise AS
SELECT
    NULL::integer AS id,
    NULL::text AS description,
    NULL::integer AS app_userid,
    NULL::integer AS category_id,
    NULL::timestamp(6) without time zone AS start_date,
    NULL::timestamp(6) without time zone AS end_date,
    NULL::real AS starting_price,
    NULL::real AS bid_step,
    NULL::integer AS auction_stateid,
    NULL::bigint AS nombre_mise,
    NULL::real AS max_amount;


ALTER TABLE public.v_auction_with_mise OWNER TO enchere;

--
-- Name: v_auction_with_mise_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.v_auction_with_mise_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.v_auction_with_mise_seq OWNER TO enchere;

--
-- Name: v_auction_with_state; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.v_auction_with_state (
    id bigint NOT NULL,
    bid_step real,
    description character varying(255) NOT NULL,
    end_date timestamp(6) without time zone NOT NULL,
    start_date timestamp(6) without time zone NOT NULL,
    starting_price real NOT NULL,
    title character varying(255),
    app_user_id bigint,
    state_id bigint,
    category_id bigint
);


ALTER TABLE public.v_auction_with_state OWNER TO enchere;

--
-- Name: v_auction_with_top_bid; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.v_auction_with_top_bid (
    auction_id bigint NOT NULL,
    commission_amount real,
    description character varying(255),
    end_date timestamp with time zone,
    start_date timestamp with time zone,
    starting_price real,
    title character varying(255),
    app_user_id bigint,
    commission_rate_id bigint,
    state_id bigint,
    top_bid_id bigint
);


ALTER TABLE public.v_auction_with_top_bid OWNER TO enchere;

--
-- Name: v_auctions_stats; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.v_auctions_stats (
    id bigint NOT NULL,
    date date,
    ended integer,
    started integer
);


ALTER TABLE public.v_auctions_stats OWNER TO enchere;

--
-- Name: v_daily_turnover; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.v_daily_turnover (
    id bigint NOT NULL,
    date date,
    turnover real
);


ALTER TABLE public.v_daily_turnover OWNER TO enchere;

--
-- Name: admin id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin ALTER COLUMN id SET DEFAULT nextval('public.admin_id_seq'::regclass);


--
-- Name: admin_token id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token ALTER COLUMN id SET DEFAULT nextval('public.admin_token_id_seq'::regclass);


--
-- Name: app_user id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq'::regclass);


--
-- Name: app_user_recharge_request id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_request ALTER COLUMN id SET DEFAULT nextval('public.app_user_recharge_request_id_seq'::regclass);


--
-- Name: app_user_recharge_state_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_state_history ALTER COLUMN id SET DEFAULT nextval('public.app_user_recharge_state_history_id_seq'::regclass);


--
-- Name: app_user_token id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token ALTER COLUMN id SET DEFAULT nextval('public.app_user_token_id_seq'::regclass);


--
-- Name: auction id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction ALTER COLUMN id SET DEFAULT nextval('public.auction_id_seq'::regclass);


--
-- Name: auction_state id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_state ALTER COLUMN id SET DEFAULT nextval('public.auction_state_id_seq'::regclass);


--
-- Name: bid_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history ALTER COLUMN id SET DEFAULT nextval('public.bid_history_id_seq'::regclass);


--
-- Name: bid_photos id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_photos ALTER COLUMN id SET DEFAULT nextval('public.bid_photos_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: commision id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.commision ALTER COLUMN id SET DEFAULT nextval('public.commision_id_seq'::regclass);


--
-- Name: daily_auction id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_auction ALTER COLUMN id SET DEFAULT nextval('public.daily_auction_id_seq'::regclass);


--
-- Name: daily_sales id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_sales ALTER COLUMN id SET DEFAULT nextval('public.daily_sales_id_seq'::regclass);


--
-- Name: recharge_state id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.recharge_state ALTER COLUMN id SET DEFAULT nextval('public.recharge_state_id_seq'::regclass);


--
-- Name: reload_request id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request ALTER COLUMN id SET DEFAULT nextval('public.reload_request_id_seq'::regclass);


--
-- Name: reload_request_state_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history ALTER COLUMN id SET DEFAULT nextval('public.reload_request_state_history_id_seq'::regclass);


--
-- Name: setting id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.setting ALTER COLUMN id SET DEFAULT nextval('public.setting_id_seq'::regclass);


--
-- Name: setting_value_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.setting_value_history ALTER COLUMN id SET DEFAULT nextval('public.setting_value_history_id_seq'::regclass);


--
-- Name: settings id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings ALTER COLUMN id SET DEFAULT nextval('public.settings_id_seq'::regclass);


--
-- Name: settings_value_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings_value_history ALTER COLUMN id SET DEFAULT nextval('public.settings_value_history_id_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.admin (id, username, email, password) FROM stdin;
\.


--
-- Data for Name: admin_token; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.admin_token (id, value, expiration_date, creation_date, adminid) FROM stdin;
\.


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.app_user (id, username, email, password, account_balance) FROM stdin;
1	Rojo	rojorabe@gmail.com	12345678	94000
\.


--
-- Data for Name: app_user_recharge_request; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.app_user_recharge_request (id, date, amount, app_userid) FROM stdin;
1	2023-01-20	100000	1
\.


--
-- Data for Name: app_user_recharge_state_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.app_user_recharge_state_history (id, date, app_user_recharge_requestid, recharge_stateid) FROM stdin;
1	2023-01-20	1	1
\.


--
-- Data for Name: app_user_token; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.app_user_token (id, value, expiration_date, creation_date, app_userid) FROM stdin;
2003	d77091eb9170123468e1c5e6680c11824d20217c	2023-02-02 23:30:21.402108	2023-02-02 22:30:21.40809	1
\.


--
-- Data for Name: auction; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.auction (id, description, app_userid, category_id, start_date, end_date, starting_price, bid_step, auction_stateid) FROM stdin;
1	One of the best 2021	1	1	2023-01-20 17:00:00	2023-01-20 17:29:59	5000	1000	1
2	One of the best 2022	1	1	2023-01-17 00:00:00	2023-01-19 17:29:59	5000	1000	2
152		1	1	2023-01-29 14:49:22.326904	2023-02-05 18:45:00	3000	0	1
\.


--
-- Data for Name: auction_mised; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.auction_mised (id, bid_step, description, end_date, max_mise, mise, nombre_mise, start_date, starting_price, app_userid, auction_stateid, category_id) FROM stdin;
\.


--
-- Data for Name: auction_state; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.auction_state (id, value) FROM stdin;
1	in progress
2	finished
3	canceled
\.


--
-- Data for Name: bid_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.bid_history (id, amount, date, app_userid, bidid, app_user_id, auction_id) FROM stdin;
1	6000	2023-01-19 00:00:00	1	2	\N	\N
\.


--
-- Data for Name: bid_photos; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.bid_photos (id, bid_id, picture) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.category (id, name) FROM stdin;
1	Oeuvre
\.


--
-- Data for Name: commision; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.commision (id, amount, parametersid, auctionid) FROM stdin;
\.


--
-- Data for Name: daily_auction; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.daily_auction (id, amount, date) FROM stdin;
\.


--
-- Data for Name: daily_sales; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.daily_sales (id, amount, date) FROM stdin;
\.


--
-- Data for Name: recharge_state; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.recharge_state (id, description) FROM stdin;
1	in progresse
2	finished
3	canceled
\.


--
-- Data for Name: reload_request; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.reload_request (id, amount, date, app_user_id) FROM stdin;
\.


--
-- Data for Name: reload_request_state_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.reload_request_state_history (id, date, reload_request_id, reload_state_id) FROM stdin;
\.


--
-- Data for Name: reload_state; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.reload_state (id, description) FROM stdin;
\.


--
-- Data for Name: setting; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.setting (id, key, creation_date) FROM stdin;
\.


--
-- Data for Name: setting_value_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.setting_value_history (id, update_date, value, setiingid) FROM stdin;
\.


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.settings (id, creation_date, key) FROM stdin;
\.


--
-- Data for Name: settings_value_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.settings_value_history (id, date, value, settings_id) FROM stdin;
\.


--
-- Data for Name: v_auction_with_state; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.v_auction_with_state (id, bid_step, description, end_date, start_date, starting_price, title, app_user_id, state_id, category_id) FROM stdin;
\.


--
-- Data for Name: v_auction_with_top_bid; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.v_auction_with_top_bid (auction_id, commission_amount, description, end_date, start_date, starting_price, title, app_user_id, commission_rate_id, state_id, top_bid_id) FROM stdin;
\.


--
-- Data for Name: v_auctions_stats; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.v_auctions_stats (id, date, ended, started) FROM stdin;
\.


--
-- Data for Name: v_daily_turnover; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.v_daily_turnover (id, date, turnover) FROM stdin;
\.


--
-- Name: admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_id_seq', 1, false);


--
-- Name: admin_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_seq', 1, false);


--
-- Name: admin_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_token_id_seq', 1, false);


--
-- Name: admin_token_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_token_seq', 1, false);


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_id_seq', 1, true);


--
-- Name: app_user_recharge_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_request_id_seq', 1, true);


--
-- Name: app_user_recharge_request_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_request_seq', 1, false);


--
-- Name: app_user_recharge_state_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_state_history_id_seq', 1, true);


--
-- Name: app_user_recharge_state_history_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_state_history_seq', 1, false);


--
-- Name: app_user_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_seq', 1, false);


--
-- Name: app_user_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_token_id_seq', 1, false);


--
-- Name: app_user_token_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_token_seq', 2051, true);


--
-- Name: auction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_id_seq', 2, true);


--
-- Name: auction_mised_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_mised_seq', 1, false);


--
-- Name: auction_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_seq', 201, true);


--
-- Name: auction_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_state_id_seq', 1, true);


--
-- Name: auction_state_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_state_seq', 1, false);


--
-- Name: bid_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.bid_history_id_seq', 1, false);


--
-- Name: bid_history_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.bid_history_seq', 1, false);


--
-- Name: bid_photos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.bid_photos_id_seq', 1, false);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.category_id_seq', 1, true);


--
-- Name: category_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.category_seq', 1, false);


--
-- Name: commision_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.commision_id_seq', 1, false);


--
-- Name: daily_auction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.daily_auction_id_seq', 1, false);


--
-- Name: daily_auction_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.daily_auction_seq', 1, false);


--
-- Name: daily_sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.daily_sales_id_seq', 1, false);


--
-- Name: recharge_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.recharge_state_id_seq', 1, true);


--
-- Name: recharge_state_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.recharge_state_seq', 1, false);


--
-- Name: reload_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.reload_request_id_seq', 1, false);


--
-- Name: reload_request_state_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.reload_request_state_history_id_seq', 1, false);


--
-- Name: reload_state_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.reload_state_seq', 1, false);


--
-- Name: setting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.setting_id_seq', 1, false);


--
-- Name: setting_value_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.setting_value_history_id_seq', 1, false);


--
-- Name: settings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.settings_id_seq', 1, false);


--
-- Name: settings_value_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.settings_value_history_id_seq', 1, false);


--
-- Name: v_app_user_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.v_app_user_seq', 1, false);


--
-- Name: v_auction_with_mise_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.v_auction_with_mise_seq', 1, false);


--
-- Name: admin admin_email_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_email_key UNIQUE (email);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: admin_token admin_token_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token
    ADD CONSTRAINT admin_token_pkey PRIMARY KEY (id);


--
-- Name: admin admin_username_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_username_key UNIQUE (username);


--
-- Name: app_user app_user_email_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_email_key UNIQUE (email);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: app_user_recharge_request app_user_recharge_request_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_request
    ADD CONSTRAINT app_user_recharge_request_pkey PRIMARY KEY (id);


--
-- Name: app_user_recharge_state_history app_user_recharge_state_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_state_history
    ADD CONSTRAINT app_user_recharge_state_history_pkey PRIMARY KEY (id);


--
-- Name: app_user_token app_user_token_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token
    ADD CONSTRAINT app_user_token_pkey PRIMARY KEY (id);


--
-- Name: app_user app_user_username_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_username_key UNIQUE (username);


--
-- Name: auction_mised auction_mised_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_mised
    ADD CONSTRAINT auction_mised_pkey PRIMARY KEY (id);


--
-- Name: auction auction_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT auction_pkey PRIMARY KEY (id);


--
-- Name: auction_state auction_state_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_state
    ADD CONSTRAINT auction_state_pkey PRIMARY KEY (id);


--
-- Name: bid_history bid_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT bid_history_pkey PRIMARY KEY (id);


--
-- Name: bid_photos bid_photos_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_photos
    ADD CONSTRAINT bid_photos_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: commision commision_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.commision
    ADD CONSTRAINT commision_pkey PRIMARY KEY (id);


--
-- Name: daily_auction daily_auction_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_auction
    ADD CONSTRAINT daily_auction_pkey PRIMARY KEY (id);


--
-- Name: daily_sales daily_sales_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_sales
    ADD CONSTRAINT daily_sales_pkey PRIMARY KEY (id);


--
-- Name: setting parameters_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.setting
    ADD CONSTRAINT parameters_pkey PRIMARY KEY (id);


--
-- Name: recharge_state recharge_state_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.recharge_state
    ADD CONSTRAINT recharge_state_pkey PRIMARY KEY (id);


--
-- Name: reload_request reload_request_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request
    ADD CONSTRAINT reload_request_pkey PRIMARY KEY (id);


--
-- Name: reload_request_state_history reload_request_state_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history
    ADD CONSTRAINT reload_request_state_history_pkey PRIMARY KEY (id);


--
-- Name: reload_state reload_state_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_state
    ADD CONSTRAINT reload_state_pkey PRIMARY KEY (id);


--
-- Name: setting_value_history setting_value_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.setting_value_history
    ADD CONSTRAINT setting_value_history_pkey PRIMARY KEY (id);


--
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- Name: settings_value_history settings_value_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings_value_history
    ADD CONSTRAINT settings_value_history_pkey PRIMARY KEY (id);


--
-- Name: v_auction_with_state v_auction_with_state_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_state
    ADD CONSTRAINT v_auction_with_state_pkey PRIMARY KEY (id);


--
-- Name: v_auction_with_top_bid v_auction_with_top_bid_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_top_bid
    ADD CONSTRAINT v_auction_with_top_bid_pkey PRIMARY KEY (auction_id);


--
-- Name: v_auctions_stats v_auctions_stats_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auctions_stats
    ADD CONSTRAINT v_auctions_stats_pkey PRIMARY KEY (id);


--
-- Name: v_daily_turnover v_daily_turnover_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_daily_turnover
    ADD CONSTRAINT v_daily_turnover_pkey PRIMARY KEY (id);


--
-- Name: auction_state_id; Type: INDEX; Schema: public; Owner: enchere
--

CREATE INDEX auction_state_id ON public.auction_state USING btree (id);


--
-- Name: setting_value_history_id; Type: INDEX; Schema: public; Owner: enchere
--

CREATE INDEX setting_value_history_id ON public.setting_value_history USING btree (id);


--
-- Name: v_auction_with_mise _RETURN; Type: RULE; Schema: public; Owner: enchere
--

CREATE OR REPLACE VIEW public.v_auction_with_mise AS
 SELECT a.id,
    a.description,
    a.app_userid,
    a.category_id,
    a.start_date,
    a.end_date,
    a.starting_price,
    a.bid_step,
    a.auction_stateid,
    count(bh.auction_id) AS nombre_mise,
    max(bh.amount) AS max_amount
   FROM (public.auction a
     LEFT JOIN public.bid_history bh ON ((a.id = bh.auction_id)))
  WHERE (a.auction_stateid = 1)
  GROUP BY bh.auction_id, a.id
  ORDER BY a.start_date DESC;


--
-- Name: v_auction_with_top_bid fk2p96qcsask0q9sfohb96aaoww; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_top_bid
    ADD CONSTRAINT fk2p96qcsask0q9sfohb96aaoww FOREIGN KEY (top_bid_id) REFERENCES public.bid_history(id);


--
-- Name: app_user_recharge_state_history fk3h347iq1irow5k7waa9sivjyl; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_state_history
    ADD CONSTRAINT fk3h347iq1irow5k7waa9sivjyl FOREIGN KEY (app_user_recharge_requestid) REFERENCES public.app_user_recharge_request(id);


--
-- Name: bid_history fk3lc852lybkc6m75vxn3ffnpir; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fk3lc852lybkc6m75vxn3ffnpir FOREIGN KEY (bidid) REFERENCES public.auction(id);


--
-- Name: admin_token fk45tsmp4upmy0cqnx24ogofg5l; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token
    ADD CONSTRAINT fk45tsmp4upmy0cqnx24ogofg5l FOREIGN KEY (adminid) REFERENCES public.admin(id);


--
-- Name: auction_mised fk47yf1g56hg5qki83lvihs3v9g; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_mised
    ADD CONSTRAINT fk47yf1g56hg5qki83lvihs3v9g FOREIGN KEY (auction_stateid) REFERENCES public.auction_state(id);


--
-- Name: settings_value_history fk4hd3e5g7v3jgmfxyy616wlmdj; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings_value_history
    ADD CONSTRAINT fk4hd3e5g7v3jgmfxyy616wlmdj FOREIGN KEY (settings_id) REFERENCES public.settings(id);


--
-- Name: reload_request_state_history fk6111b1plo1cumr8gdxmppf7ra; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history
    ADD CONSTRAINT fk6111b1plo1cumr8gdxmppf7ra FOREIGN KEY (reload_request_id) REFERENCES public.reload_request(id);


--
-- Name: bid_history fk6y2xcg8y0hipt4u4jcjf7jbvj; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fk6y2xcg8y0hipt4u4jcjf7jbvj FOREIGN KEY (auction_id) REFERENCES public.auction(id);


--
-- Name: app_user_recharge_state_history fk7cpdka9p8q6ctx2r4teyfypja; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_state_history
    ADD CONSTRAINT fk7cpdka9p8q6ctx2r4teyfypja FOREIGN KEY (recharge_stateid) REFERENCES public.recharge_state(id);


--
-- Name: reload_request_state_history fk92xvtrbkbbe2otemk9lh9yc69; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history
    ADD CONSTRAINT fk92xvtrbkbbe2otemk9lh9yc69 FOREIGN KEY (reload_state_id) REFERENCES public.reload_state(id);


--
-- Name: v_auction_with_top_bid fk9jo0dtghod0wxanvcqssxh3lk; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_top_bid
    ADD CONSTRAINT fk9jo0dtghod0wxanvcqssxh3lk FOREIGN KEY (commission_rate_id) REFERENCES public.settings_value_history(id);


--
-- Name: admin_token fkadmin_toke154889; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token
    ADD CONSTRAINT fkadmin_toke154889 FOREIGN KEY (adminid) REFERENCES public.admin(id);


--
-- Name: app_user_recharge_state_history fkapp_user_r279062; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_state_history
    ADD CONSTRAINT fkapp_user_r279062 FOREIGN KEY (recharge_stateid) REFERENCES public.recharge_state(id);


--
-- Name: app_user_recharge_state_history fkapp_user_r630701; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_state_history
    ADD CONSTRAINT fkapp_user_r630701 FOREIGN KEY (app_user_recharge_requestid) REFERENCES public.app_user_recharge_request(id);


--
-- Name: app_user_recharge_request fkapp_user_r80032; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_request
    ADD CONSTRAINT fkapp_user_r80032 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: app_user_token fkapp_user_t445450; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token
    ADD CONSTRAINT fkapp_user_t445450 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: auction fkauction314945; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkauction314945 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: auction fkauction439414; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkauction439414 FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: auction fkauction807511; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkauction807511 FOREIGN KEY (auction_stateid) REFERENCES public.auction_state(id);


--
-- Name: app_user_recharge_request fkaw4fche0898prj4i4eptphkk9; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_recharge_request
    ADD CONSTRAINT fkaw4fche0898prj4i4eptphkk9 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: auction_mised fkbbejw452b4m608a00j856jeah; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_mised
    ADD CONSTRAINT fkbbejw452b4m608a00j856jeah FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: bid_history fkbid_histor407877; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fkbid_histor407877 FOREIGN KEY (bidid) REFERENCES public.auction(id);


--
-- Name: bid_history fkbid_histor887760; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fkbid_histor887760 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: commision fkcommision295386; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.commision
    ADD CONSTRAINT fkcommision295386 FOREIGN KEY (parametersid) REFERENCES public.setting(id);


--
-- Name: commision fkcommision804390; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.commision
    ADD CONSTRAINT fkcommision804390 FOREIGN KEY (auctionid) REFERENCES public.auction(id);


--
-- Name: app_user_token fkdrmoqofasods84jfbmkge0j7w; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token
    ADD CONSTRAINT fkdrmoqofasods84jfbmkge0j7w FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: v_auction_with_top_bid fkf8upatx6j4320lujc8ch8o9tx; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_top_bid
    ADD CONSTRAINT fkf8upatx6j4320lujc8ch8o9tx FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: v_auction_with_top_bid fkgtx5dqfnck0ts42pdddt96k37; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_top_bid
    ADD CONSTRAINT fkgtx5dqfnck0ts42pdddt96k37 FOREIGN KEY (state_id) REFERENCES public.auction_state(id);


--
-- Name: reload_request fkhnh7k0tobwt2jh83qy9ewfnfo; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request
    ADD CONSTRAINT fkhnh7k0tobwt2jh83qy9ewfnfo FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: auction fkhq6yj95ecwfle2h477um77te7; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkhq6yj95ecwfle2h477um77te7 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: v_auction_with_state fklif9usubqcssayrfjjk631myx; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_state
    ADD CONSTRAINT fklif9usubqcssayrfjjk631myx FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: bid_history fkme36cdh4ejg15rdl15asqups6; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fkme36cdh4ejg15rdl15asqups6 FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: v_auction_with_state fkmjro46c7dvs65k5s0ieuylf1l; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_state
    ADD CONSTRAINT fkmjro46c7dvs65k5s0ieuylf1l FOREIGN KEY (state_id) REFERENCES public.auction_state(id);


--
-- Name: auction fkn177e55w3c2qy0osa460lcmgc; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkn177e55w3c2qy0osa460lcmgc FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: v_auction_with_state fkn52ny1jvi1jddf0p01wviyji6; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_auction_with_state
    ADD CONSTRAINT fkn52ny1jvi1jddf0p01wviyji6 FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: auction_mised fknxed69cddh51b70wfnjrhldev; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_mised
    ADD CONSTRAINT fknxed69cddh51b70wfnjrhldev FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: bid_history fkokx2lgase9px5idycif457o49; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fkokx2lgase9px5idycif457o49 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: auction fkrkfxjkivfq795yy1cqybxkqv5; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkrkfxjkivfq795yy1cqybxkqv5 FOREIGN KEY (auction_stateid) REFERENCES public.auction_state(id);


--
-- Name: setting_value_history fksetting_va113721; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.setting_value_history
    ADD CONSTRAINT fksetting_va113721 FOREIGN KEY (setiingid) REFERENCES public.setting(id);


--
-- PostgreSQL database dump complete
--

